$(function () {
    $('#tt').treegrid({
        url:'/permission/queryList',
        idField:'id',
        treeField:'name',
        animate:true,
        columns:[[
            {title:'name',field:'name',width:180},
            {field:'id',title:'id',width:60,align:'right'},
            {field:'pid',title:'pid',width:80},
            {field:'date',title:'date',width:80}
        ]]
    });


    $('#addtree').on('click',function () {
        $('#tt').treegrid('append',{
            parent: 0,  // the node has a 'id' value that defined through 'idField' property
            data: [{
                id: '073',
                name: 'name73',
            }]
        });
    });

});