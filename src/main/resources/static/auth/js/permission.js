if(typeof jQuery == "undefined") throw "Unable to load ServiceProxy, jQuery not found.";
(function ($, window,basePath,undefined) {

    let selectedId; // 当前选中的id
    let newRowId = 0; // 新建节点默认id
    let editingId; // 当前编辑的id
    let permissionApp = {};

    permissionApp.fn = {
        init: function () {
            $("#tableId").treegrid({
                url:'/permission/queryList',
                method: 'post',
                iconCls: 'icon-edit',
                striped: true,
                animate:true,
                fitColumns: true,
                lines: true,
                idField:'id',
                treeField:'name',
                toolbar: permissionApp.tbar,
                onLoadSuccess: permissionApp.on.loadSuccess,
                onDblClickCell: permissionApp.on.dblClickCell,
                onClickRow: permissionApp.on.clickRow,
                onEndEdit: permissionApp.on.endEdit,
                columns:[[
                    {
                        title:'名称',
                        field:'name',
                        width:300,
                        align: 'center',
                        formatter:permissionApp.formatter.name,
                        editor:{
                            type:'textbox',
                            options:{
                                required:true
                            }
                        }
                    },
                    {
                        field:'code',
                        title:'权限值',
                        width:300,
                        align:'center,',
                        editor:{
                            type:'textbox',
                            options:{
                                required:true
                            }
                        }
                    },
                    {
                        field:'type',
                        title:'类型',
                        width:80,
                        formatter:permissionApp.formatter.type,
                        editor: {
                            type:'combobox',
                            options:{
                                data:[
                                    {
                                        value: 1,
                                        text: '菜单'
                                    },
                                    {
                                        value: 2,
                                        text: '操作'
                                    },{
                                        value: 1,
                                        text: '权限'
                                    }
                                ]
                            }
                        }
                    },
                    {
                        field:'status',
                        title:'状态',
                        width:80,
                        formatter:permissionApp.formatter.status,
                        editor: {
                            type:'combobox',
                            options:{
                                data:[
                                    {
                                        value: 0,
                                        text: '禁用'
                                    },
                                    {
                                        value: 1,
                                        text: '启用'
                                    }
                                ]
                            }
                        }
                    },
                    {
                        field:'tempstamp',
                        title:'更新时间',
                        width:300,
                        formatter:permissionApp.formatter.tempstamp,
                    },
                    {
                        title:'操作',
                        field:'hasChild',
                        align: "center",
                        formatter:permissionApp.formatter.hasChild
                    }

                ]],
            });
        },
        debug: function (opr, obj) {
            return $("#tableId").treegrid(opr,obj);
        },
        buildDTO: function (row,changes) {
            let permissionDTO = {
                'id':null,
                'pid':null,
                'name':row.name,
                'hasChild':row.hasChild,
                'status':row.status,
                'code': row.code
            };

            if (row.id === newRowId){ //新增
                permissionDTO.pid = (row._parentId) ? row._parentId : 0;
            } else { //修改
                permissionDTO.id = row.id;
                permissionDTO.name = row.name;
                permissionDTO.status = row.status;
                permissionDTO.code = row.code;
            }
            return permissionDTO;
        }
    };

    permissionApp.tbar =[
        {
            text:'添加',
            iconCls: 'icon-add',
            handler: function () {
                permissionApp.action.addRow();
            }
        },
        {
            text:'修改',
            iconCls: 'icon-edit',
            handler: function () {
                permissionApp.action.editRow();
            }
        },
        {
            text:'保存',
            iconCls: 'icon-save',
            handler: function () {
                permissionApp.action.saveEdit();
            }
        },
        {
            text:'取消',
            iconCls: 'icon-no',
            handler: function () {
                permissionApp.action.cancelEdit();
            }
        },
        {
            text:'删除',
            iconCls: 'icon-remove',
            handler: function () {
                permissionApp.action.deleteRow();
            }
        }
    ];

    permissionApp.formatter = {
        name: function (value, row) {
            if (!row.visible || row.visible === '0') {
                return '<label style="color: lightgray">'+value+'</label>'
            }else {
                return value;
            }
        },
        type: function (value, row) {
            let text = '-';
            switch (value){
                case 1:text='菜单';break;
                case 2:text='操作';break;
                case 3:text='权限';break;
            }
            return text;
        },
        status: function (value, row) {
            if(value !== 0){
                return '<span style="color: green">可用</span>';
            }else {
                return '<span style="color: red;">禁用</span>';
            }
        },
        tempstamp:function (value,row) {
          var date =  new Date();
          date.setDate(value);
          return date.format('yyyy-MM-dd HH:mm:ss')
        },
        hasChild: function (value, row) {
            value = '<span>';
            if (row.id === newRowId) {
                value += '<a href="javascript:void(0)" onclick="permissionApp.action.saveEdit();" title="提交当前新增板块">提交</a> | ' +
                    '<a  href="javascript:void(0)" onclick="permissionApp.action.cancelEdit();" title="取消当前新增板块">取消</a>'
            }else {
                value += '<a href="javascript:void(0)" onclick="permissionApp.action.editRow('+ row.id +');" title="编辑“'+ row.text +'”版块信息">编辑</a> | ';
                if (row.hasChild){
                    value += '<a href="javascript:void(0)" onclick="permissionApp.action.addRow('+ row.id +');" title="在“'+ row.name +'”节点下添加一个子版块">添加子版</a> | ';
                }
                value += '<a href="javascript:void(0)" onclick="permissionApp.action.deleteRow('+ row.id +',' + row.name + ')" title="删除'+ row.text + '版块">删除</a>';
            }
            value += '</span>';
            return value;
        }
    };

    permissionApp.action = {
        selectedRow: function (id) {
            selectedId = id;
            $("#tableId").treegrid('select',id);
        },
        addRow: function (parentId) {
            if (editingId){
                $("#tableId").action.saveEdit();
                return;
            }
            if (!parentId) {
               let row = $("#tableId").treegrid('getSelected');
               if (row) {
                   parentId = row.attributes.pid;
               }else {
                   parentId = 0;
               }
            }

            var newForums = [
                {
                    "id": newRowId,
                    "name":"",
                    "state":1,
                    "checked":false,
                    "visible":"1",
                    "hasChild":0,
                    "attributes":{"pid": parentId }
                }
            ];

            if (editingId !== newRowId){
                $("#tableId").treegrid('append', {
                    parent: parentId,
                    data: newForums
                });
            }
            permissionApp.action.editRow(newRowId);
            permissionApp.action.selectRow(newRowId);
            permissionApp.treegrid('getEditor', {id:newRowId,field:'text'}).target.next().find("input:first").focus();
        },
        editRow: function (row) {
            row = (row || row === newRowId)? row : $("#tableId").treegrid('getSelected');
            let id;
            if (typeof row == 'object' && row){
                id = row.id;
            }else if (typeof row == 'number') {
                id = row;
            }else {
                row = undefined;
            }

            if (!row && row !== newRowId){
                alert("请选中一行进行编辑！");
                return;
            }
            if (editingId !== undefined && editingId !== id && editingId !== newRowId){
                permissionApp.saveEdit();
            }
            if (editingId === undefined) {
                editingId = id;
                $("#tableId").treegrid('beginEdit', editingId);
            }
            permissionApp.action.selectRow(editingId);
        },
        saveEdit: function () {
            if(!editingId && editingId !== newRowId)return false;
            let row = $("#tableId").treegrid('getSelected');
            let id = row.id;
            if($("#tableId").treegrid('getEditor',{id:id,field:'text'}).target[0].value.length === 0){
                alert('权限名称不能为空');return false;
            }

            let ok =function () {
                $("#tableId").treegrid('endEdit',editingId);
                editingId = undefined;
                $("#tableId").treegrid('refresh',id);
            }

            let content = "确定要保存当前编辑的板块信息？";
            let leftBtnFunc = function(index){
                ok();
                layer.close(index);
            };
            let rightBtnFunc = function(index){
                layer.close(index);
            };
            let btns = ['确定', '取消'];
            if (editingId === newRowId) {
                content = "请选择当前新增板块的类型，是否为叶子节点（实际可留言板块）？</br></br><b>注意：</b></br>实际可留言板块将无法在其下继续添加子节点</br>板块创建后不可修改节点类型";
                btns = ['叶子节点', '非叶子节点'];
                leftBtnFunc = function(index){
                    row.hasChild = 0;
                    ok();
                    layer.close(index);
                };
                rightBtnFunc = function(index){
                    row.hasChild = 1;
                    ok();
                    layer.close(index);
                };
            }
            layer.open({
                type: 0,
                title: '操作确认',
                icon: 3,
                btn: btns,
                yes: leftBtnFunc,
                btn2 : rightBtnFunc,
                content: "<div style='padding:10px'>" + content + "</div>",
                maxmin: true,
                scrollbar: false
            });
            permissionApp.action.selectRow(id);

        },
        cancelEdit:function () {
            if (editingId != undefined ){
                layer.confirm("确定要放弃当前编辑的板块信息？", {icon: 3, title:'确认'}, function(index){
                    let id = editingId;
                    editingId = undefined;
                    if (id === newRowId) {
                        $("#tableId").treegrid('remove', id);
                    } else {
                        $("#tableId").treegrid('cancelEdit', id);
                        $("#tableId").treegrid('refresh', id);
                    }
                    selectedId = undefined;
                    $("#tableId").treegrid('unselectAll');
                    layer.close(index);
                });
            } else {
                selectedId = undefined;
                $("#tableId").treegrid('unselectAll');
            }
        },
        deleteRow: function (id) {
            $.post(basePath +"/permission/delete",{"fid":id,"name":name},function(_data) {
                if(_data && _data.result ==="success") {
                    $("#tableId").treegrid("remove", id);
                   alert('板块删除')
                }else {
                    console.debug(_data);
                }
            });
        }
    };

    permissionApp.on = {
        loadSuccess: function (row, data) {
            if (!data.length){
                alert('没有查询到该板块的子节点数据');
                if (row) $("#tableId").treegrid('collapse',row.id);
            }
        },
        clickRow: function (row) {
            if (undefined !== editingId && editingId !== row.id) {
                alert('选择其他行之前，请先保存正在编辑的板块');
                permissionApp.action.selectedRow(editingId);
            }else {
                permissionApp.action.selectedRow(editingId);
            }
        },
        dblClickCell: function (row) {
            if (row) {
                permissionApp.action.editRow(row);
            }
        },
        endEdit: function(row,changes){
            if (newRowId !== row.id && $.isEmptyObject(changes)){
                alert('未发现有修改的内容，将不进行提交操作');
                return false;
            }
            let permissionDTO =  permissionApp.fn.buildDTO(row,changes);
            $.ajax({
                url: basePath + '/permission/addOrUpdate',
                dataType: 'json',
                type: 'post',
                data: permissionDTO,
                success: function (res) {
                    if (res) {
                        selectedId = res.id;
                    }else {
                        alert('数据保存过程出现异常，请稍后再试');
                        permissionApp.action.editRow(row.id);
                    }
                }
            });
        },
        
    };

    window.permissionApp =permissionApp;
    $(function () {
        permissionApp.fn.init();
    })
})(jQuery,window,basePath);
