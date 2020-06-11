layui.define(['jquery','layer'], function (exports) {
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
        }
    }
    
    function req(url,data,type,func){
        $.ajax({
            url:url,
            data:data,
            type:type,
            contentType:"application/json",
            success:function (res) {
                if (res.status){
                    //后置处理
                    func(res);
                }else {
                    if (res.code == 'AUTH1008') {
                        location.href = '/login';
                    }
                    layer.msg(res.msg == null ? "操作失败" : res.msg, {icon: 2, time: 1000});
                    console.log(JSON.stringify(res))
                }

            }
        })
    }



    exports('hydra', hydra);
});
