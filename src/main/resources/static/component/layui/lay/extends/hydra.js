layui.define(['jquery','layer','table'], function (exports) {
    var $ = layui.jquery;
    var layer = layui.layer;

    var hydra = {
        post:function (url,data,func) {
            req(url,data,"post",func)
        },
        get:function (url,data,func) {
            req(url,data,"get",func)
        },
        parseData:function parseData(res){
            if (res.code == 403){
                location.href = res.data;
            }
            return {
                "code": res.code == 200 ? 0 : 1, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.records //解析数据列表
            }
        },
        convertPageParam:function convertPageParam(){
            return {
                pageName: 'page.current' //页码的参数名称，默认：page
                , limitName: 'page.size' //每页数据量的参数名，默认：limit
            }
        },
        add:function (url,data,parent,tableId) {
            updateFunc(url,data,parent,tableId);
        },
        del:function (promptMsg,url,table,tableId) {
            delFunc(promptMsg,url,table,tableId)
        },
        //批量删除
        batchDel:function (promptMsg,url,obj,table,tableId) {
            batchDelFunc(promptMsg,url,obj,table,tableId)
        },
        update:function (url,data,parent,tableId) {
            updateFunc(url,data,parent,tableId);
        },
        edit: function (url) {
            editFunc(url);
        }
    }

    function req(url,data,type,func){
        var loading = layer.load();
        $.ajax({
            url:url,
            data:data,
            type:type,
            contentType:"application/json",
            success:function (res) {
                layer.close(loading)
                if (res.status){
                    //后置处理
                    if (func) {
                        func(res);
                    }
                }else {
                    if (res.code == 'AUTH1008') {
                        location.href = '/login';
                    }
                    layer.msg(res.msg == null ? "操作失败" : res.code == '403' ? "您没有权限操作此功能" : res.msg, {icon: 2, time: 1000});
                    console.log(JSON.stringify(res))
                }

            }
        })
    }

    function delFunc(promptMsg,url,table,tableId){
        layer.confirm(promptMsg, {icon: 3, title: '提示'}, function (index) {
            layer.close(index);

            hydra.post(url, {}, function (res) {
                layer.msg("操作成功", {icon: 1, time: 1000}, function () {
                    table.reload(tableId);
                });
            })
        });
    }


    function batchDelFunc(promptMsg,url,obj,table,tableId){
        console.log(JSON.stringify(table.checkStatus(obj.config.id).data))
        let data = table.checkStatus(obj.config.id).data;
        if (data.length === 0) {
            layer.msg("未选中数据", {icon: 3, time: 1000});
            return false;
        }
        var ids = [];
        for (let i = 0; i < data.length; i++) {
            ids.push(data[i].id)
        }

        layer.confirm(promptMsg, {icon: 3, title: '提示'}, function (index) {
            layer.close(index);
            // let loading = layer.load();
            hydra.post(url, JSON.stringify(ids), function (res) {
                layer.msg(res.msg, {icon: res.status ? 1 : 2, time: 1000}, function () {
                    if (res.status) {
                        table.reload(tableId);
                    }
                });
            })
        });
    }

    function editFunc(url){
        layer.open({
            type: 2,
            title: '修改',
            shade: 0.1,
            area: ['500px', '400px'],
            content: url
        });
    }

    function updateFunc(url,data,parent,tableId){
        console.log(JSON.stringify(data.field))
        hydra.post(url,JSON.stringify(data.field),function(res){
            layer.msg(res.msg,{icon:1,time:1000},function(){
                if (tableId) {
                    parent.layui.table.reload(tableId);
                }
            });
        })
    }
    exports('hydra', hydra);
});
