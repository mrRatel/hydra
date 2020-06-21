package com.ratel.hydra;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class HydraApplicationTests {

    @Test
    void contextLoads() {
        Document document = Jsoup.parse("<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\" xmlns:http=\"http://www.w3.org/1999/xhtml\" xmlns:sec=\"http://www.thymeleaf.org/thymeleaf-extras-springsecurity4\">\n" +
                "    <!-- th:fragment 定义需要引入的代码块，该div包含的内容即为引用的公共内容 -->\n" +
                "    <div th:fragment=\"menu\">\n" +
                "        <!-- 侧边菜单 -->\n" +
                "        <div class=\"layui-side layui-side-menu\">\n" +
                "            <div class=\"layui-side-scroll\">\n" +
                "                <div class=\"layui-logo\" lay-href=\"home/console.html\">\n" +
                "                    <span>HydraAdmin</span>\n" +
                "                </div>\n" +
                "\n" +
                "                <ul class=\"layui-nav layui-nav-tree\" lay-shrink=\"all\" id=\"LAY-system-side-menu\" lay-filter=\"layadmin-system-side-menu\">\n" +
                "\n" +
                "                    <li data-name=\"home\" class=\"layui-nav-item layui-nav-itemed\" th:each=\"menu:${menuTree}\">\n" +
                "                        <a href=\"javascript:;\" lay-tips=\"主页\" lay-direction=\"2\">\n" +
                "                            <i class=\"layui-icon layui-icon-home\"></i>\n" +
                "                            <cite th:text=\"${menu}\">123123</cite>\n" +
                "                        </a>\n" +
                "                    </li>\n" +
                "\n" +
                "\n" +
                "                    <li data-name=\"home\" class=\"layui-nav-item layui-nav-itemed\">\n" +
                "                        <a href=\"javascript:;\" lay-tips=\"主页\" lay-direction=\"2\">\n" +
                "                            <i class=\"layui-icon layui-icon-home\"></i>\n" +
                "                            <cite>主页</cite>\n" +
                "                        </a>\n" +
                "                        <dl class=\"layui-nav-child\">\n" +
                "                            <dd data-name=\"console\" class=\"layui-this\">\n" +
                "                                <a lay-href=\"home/console.html\">控制台</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"console\">\n" +
                "                                <a lay-href=\"home/homepage1.html\">主页一</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"console\">\n" +
                "                                <a lay-href=\"home/homepage2.html\">主页二</a>\n" +
                "                            </dd>\n" +
                "                        </dl>\n" +
                "                    </li>\n" +
                "                    <li data-name=\"component\" class=\"layui-nav-item\">\n" +
                "                        <a href=\"javascript:;\" lay-tips=\"组件\" lay-direction=\"2\">\n" +
                "                            <i class=\"layui-icon layui-icon-component\"></i>\n" +
                "                            <cite>组件</cite>\n" +
                "                        </a>\n" +
                "                        <dl class=\"layui-nav-child\">\n" +
                "                            <dd data-name=\"grid\">\n" +
                "                                <a href=\"javascript:;\">栅格</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd data-name=\"list\"><a lay-href=\"component/grid/list.html\">等比例列表排列</a></dd>\n" +
                "                                    <dd data-name=\"mobile\"><a lay-href=\"component/grid/mobile.html\">按移动端排列</a></dd>\n" +
                "                                    <dd data-name=\"mobile-pc\"><a lay-href=\"component/grid/mobile-pc.html\">移动桌面端组合</a></dd>\n" +
                "                                    <dd data-name=\"all\"><a lay-href=\"component/grid/all.html\">全端复杂组合</a></dd>\n" +
                "                                    <dd data-name=\"stack\"><a lay-href=\"component/grid/stack.html\">低于桌面堆叠排列</a></dd>\n" +
                "                                    <dd data-name=\"speed-dial\"><a lay-href=\"component/grid/speed-dial.html\">九宫格</a></dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"button\">\n" +
                "                                <a lay-href=\"component/button/index.html\">按钮</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"form\">\n" +
                "                                <a href=\"javascript:;\">表单</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd><a lay-href=\"component/form/list.html\">表单元素</a></dd>\n" +
                "                                    <dd><a lay-href=\"component/form/group.html\">表单组合</a></dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"nav\">\n" +
                "                                <a lay-href=\"component/nav/index.html\">导航</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"tabs\">\n" +
                "                                <a lay-href=\"component/tabs/index.html\">选项卡</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"progress\">\n" +
                "                                <a lay-href=\"component/progress/index.html\">进度条</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"panel\">\n" +
                "                                <a lay-href=\"component/panel/index.html\">面板</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"badge\">\n" +
                "                                <a lay-href=\"component/badge/index.html\">徽章</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"timeline\">\n" +
                "                                <a lay-href=\"component/timeline/index.html\">时间线</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"anim\">\n" +
                "                                <a lay-href=\"component/anim/index.html\">动画</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"auxiliar\">\n" +
                "                                <a lay-href=\"component/auxiliar/index.html\">辅助</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"layer\">\n" +
                "                                <a href=\"javascript:;\">通用弹层<span class=\"layui-nav-more\"></span></a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd data-name=\"list\">\n" +
                "                                        <a lay-href=\"component/layer/list.html\" lay-text=\"layer 功能演示\">功能演示</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"special-demo\">\n" +
                "                                        <a lay-href=\"component/layer/special-demo.html\" lay-text=\"layer 特殊示例\">特殊示例</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"theme\">\n" +
                "                                        <a lay-href=\"component/layer/theme.html\" lay-text=\"layer 风格定制\">风格定制</a>\n" +
                "                                    </dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"laydate\">\n" +
                "                                <a href=\"javascript:;\">日期时间</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd data-name=\"demo1\">\n" +
                "                                        <a lay-href=\"component/laydate/demo1.html\" lay-text=\"layDate 功能演示一\">功能演示一</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"demo2\">\n" +
                "                                        <a lay-href=\"component/laydate/demo2.html\" lay-text=\"layDate 功能演示二\">功能演示二</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"theme\">\n" +
                "                                        <a lay-href=\"component/laydate/theme.html\" lay-text=\"layDate 设定主题\">设定主题</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"special-demo\">\n" +
                "                                        <a lay-href=\"component/laydate/special-demo.html\" lay-text=\"layDate 特殊示例\">特殊示例</a>\n" +
                "                                    </dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"table-static\">\n" +
                "                                <a lay-href=\"component/table/static.html\">静态表格</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"table\">\n" +
                "                                <a href=\"javascript:;\">数据表格</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd data-name=\"simple\">\n" +
                "                                        <a lay-href=\"component/table/simple.html\" lay-text=\"\">简单数据表格</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"auto\">\n" +
                "                                        <a lay-href=\"component/table/auto.html\" lay-text=\"\">列宽自动分配</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"data\">\n" +
                "                                        <a lay-href=\"component/table/data.html\" lay-text=\"\">赋值已知数据</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"tostatic\">\n" +
                "                                        <a lay-href=\"component/table/tostatic.html\" lay-text=\"\">转化静态表格</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"page\">\n" +
                "                                        <a lay-href=\"component/table/page.html\" lay-text=\"\">开启分页</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"resetPage\">\n" +
                "                                        <a lay-href=\"component/table/resetPage.html\" lay-text=\"\">自定义分页</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"page\">\n" +
                "                                        <a lay-href=\"component/table/headerTool.html\" lay-text=\"\">开启头部工具栏</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"page\">\n" +
                "                                        <a lay-href=\"component/table/totalRows.html\" lay-text=\"\">开启行合计</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"height\">\n" +
                "                                        <a lay-href=\"component/table/height.html\" lay-text=\"\">高度最大适应</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"checkbox\">\n" +
                "                                        <a lay-href=\"component/table/checkbox.html\" lay-text=\"\">开启复选框</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"checkbox\">\n" +
                "                                        <a lay-href=\"component/table/radio.html\" lay-text=\"\">开启单选框</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"checkbox\">\n" +
                "                                        <a lay-href=\"component/table/treeTable.html\" lay-text=\"\">树形表格</a>\n" +
                "                                    </dd>\n" +
                "\n" +
                "                                    <dd data-name=\"cellEdit\">\n" +
                "                                        <a lay-href=\"component/table/cellEdit.html\" lay-text=\"\">开启单元格编辑</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"form\">\n" +
                "                                        <a lay-href=\"component/table/form.html\" lay-text=\"\">加入表单元素</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"style\">\n" +
                "                                        <a lay-href=\"component/table/style.html\" lay-text=\"\">设置单元格样式</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"fixed\">\n" +
                "                                        <a lay-href=\"component/table/fixed.html\" lay-text=\"\">固定列</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"operate\">\n" +
                "                                        <a lay-href=\"component/table/operate.html\" lay-text=\"\">数据操作</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"operate\">\n" +
                "                                        <a lay-href=\"component/table/cellRow.html\" lay-text=\"\">监听行事件</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"reload\">\n" +
                "                                        <a lay-href=\"component/table/reload.html\" lay-text=\"\">数据表格的重载</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"initSort\">\n" +
                "                                        <a lay-href=\"component/table/initSort.html\" lay-text=\"\">设置初始排序</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"cellEvent\">\n" +
                "                                        <a lay-href=\"component/table/cellEvent.html\" lay-text=\"\">监听单元格事件</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"thead\">\n" +
                "                                        <a lay-href=\"component/table/thead.html\" lay-text=\"\">复杂表头</a>\n" +
                "                                    </dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"laypage\">\n" +
                "                                <a href=\"javascript:;\">分页</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd data-name=\"demo1\">\n" +
                "                                        <a lay-href=\"component/laypage/demo1.html\" lay-text=\"layPage 功能演示一\">功能演示一</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"demo2\">\n" +
                "                                        <a lay-href=\"component/laypage/demo2.html\" lay-text=\"layPage 功能演示二\">功能演示二</a>\n" +
                "                                    </dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"upload\">\n" +
                "                                <a href=\"javascript:;\">上传</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd data-name=\"demo1\">\n" +
                "                                        <a lay-href=\"component/upload/demo1.html\" lay-text=\"上传功能演示一\">功能演示一</a>\n" +
                "                                    </dd>\n" +
                "                                    <dd data-name=\"demo2\">\n" +
                "                                        <a lay-href=\"component/upload/demo2.html\" lay-text=\"上传功能演示二\">功能演示二</a>\n" +
                "                                    </dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"rate\">\n" +
                "                                <a lay-href=\"component/rate/index.html\">评分</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"carousel\">\n" +
                "                                <a lay-href=\"component/carousel/index.html\">轮播</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"flow\">\n" +
                "                                <a lay-href=\"component/flow/index.html\">流加载</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"util\">\n" +
                "                                <a lay-href=\"component/util/index.html\">工具</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"code\">\n" +
                "                                <a lay-href=\"component/code/index.html\">代码修饰</a>\n" +
                "                            </dd>\n" +
                "                        </dl>\n" +
                "                    </li>\n" +
                "                    <li data-name=\"template\" class=\"layui-nav-item\">\n" +
                "                        <a href=\"javascript:;\" lay-tips=\"页面\" lay-direction=\"2\">\n" +
                "                            <i class=\"layui-icon layui-icon-template\"></i>\n" +
                "                            <cite>页面</cite>\n" +
                "                        </a>\n" +
                "                        <dl class=\"layui-nav-child\">\n" +
                "                            <dd><a lay-href=\"template/personalpage.html\">个人主页</a></dd>\n" +
                "                            <dd><a lay-href=\"template/addresslist.html\">通讯录</a></dd>\n" +
                "                            <dd><a lay-href=\"template/goodslist.html\">商品列表</a></dd>\n" +
                "                            <dd><a lay-href=\"template/msgboard.html\">留言板</a></dd>\n" +
                "                            <dd><a lay-href=\"template/search.html\">搜索结果</a></dd>\n" +
                "                            <dd><a href=\"user/reg.html\" target=\"_blank\">注册</a></dd>\n" +
                "                            <dd><a href=\"user/login.html\" target=\"_blank\">登入</a></dd>\n" +
                "                            <dd><a href=\"user/forget.html\" target=\"_blank\">忘记密码</a></dd>\n" +
                "                            <dd><a lay-href=\"template/tips/404.html\">404页面不存在</a></dd>\n" +
                "                            <dd><a lay-href=\"template/tips/error.html\">错误提示</a></dd>\n" +
                "                            <dd><a lay-href=\"http://www.baidu.com/\">百度一下</a></dd>\n" +
                "                            <dd><a lay-href=\"http://www.layui.com/\">layui官网</a></dd>\n" +
                "                            <dd><a lay-href=\"http://www.layui.com/admin/\">layuiAdmin官网</a></dd>\n" +
                "                        </dl>\n" +
                "                    </li>\n" +
                "                    <li data-name=\"app\" class=\"layui-nav-item\">\n" +
                "                        <a href=\"javascript:;\" lay-tips=\"应用\" lay-direction=\"2\">\n" +
                "                            <i class=\"layui-icon layui-icon-app\"></i>\n" +
                "                            <cite>应用</cite>\n" +
                "                        </a>\n" +
                "                        <dl class=\"layui-nav-child\">\n" +
                "\n" +
                "                            <dd data-name=\"content\">\n" +
                "                                <a href=\"javascript:;\">内容系统</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd data-name=\"list\"><a lay-href=\"app/content/list.html\">文章列表</a></dd>\n" +
                "                                    <dd data-name=\"tags\"><a lay-href=\"app/content/tags.html\">分类管理</a></dd>\n" +
                "                                    <dd data-name=\"comment\"><a lay-href=\"app/content/comment.html\">评论管理</a></dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"forum\">\n" +
                "                                <a href=\"javascript:;\">社区系统</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd data-name=\"list\"><a lay-href=\"app/forum/list.html\">帖子列表</a></dd>\n" +
                "                                    <dd data-name=\"replys\"><a lay-href=\"app/forum/replys.html\">回帖列表</a></dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd>\n" +
                "                                <a lay-href=\"app/message/index.html\">消息中心</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"workorder\">\n" +
                "                                <a lay-href=\"app/workorder/list.html\">工单系统</a>\n" +
                "                            </dd>\n" +
                "                        </dl>\n" +
                "                    </li>\n" +
                "                    <li data-name=\"senior\" class=\"layui-nav-item\">\n" +
                "                        <a href=\"javascript:;\" lay-tips=\"高级\" lay-direction=\"2\">\n" +
                "                            <i class=\"layui-icon layui-icon-senior\"></i>\n" +
                "                            <cite>高级</cite>\n" +
                "                        </a>\n" +
                "                        <dl class=\"layui-nav-child\">\n" +
                "                            <dd>\n" +
                "                                <a layadmin-event=\"im\">LayIM 通讯系统</a>\n" +
                "                            </dd>\n" +
                "                            <dd data-name=\"echarts\">\n" +
                "                                <a href=\"javascript:;\">Echarts集成</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd><a lay-href=\"senior/echarts/line.html\">折线图</a></dd>\n" +
                "                                    <dd><a lay-href=\"senior/echarts/bar.html\">柱状图</a></dd>\n" +
                "                                    <dd><a lay-href=\"senior/echarts/map.html\">地图</a></dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                        </dl>\n" +
                "                    </li>\n" +
                "                    <li data-name=\"user\" class=\"layui-nav-item\">\n" +
                "                        <a href=\"javascript:;\" lay-tips=\"用户\" lay-direction=\"2\">\n" +
                "                            <i class=\"layui-icon layui-icon-user\"></i>\n" +
                "                            <cite>用户</cite>\n" +
                "                        </a>\n" +
                "                        <dl class=\"layui-nav-child\">\n" +
                "                            <dd>\n" +
                "                                <a lay-href=\"user/user/list.html\">网站用户</a>\n" +
                "                            </dd>\n" +
                "                            <dd>\n" +
                "                                <a lay-href=\"user/administrators/list.html\">后台管理员</a>\n" +
                "                            </dd>\n" +
                "                            <dd>\n" +
                "                                <a lay-href=\"user/administrators/loginLog.html\">角色管理</a>\n" +
                "                            </dd>\n" +
                "                        </dl>\n" +
                "                    </li>\n" +
                "                    <li data-name=\"set\" class=\"layui-nav-item\">\n" +
                "                        <a href=\"javascript:;\" lay-tips=\"设置\" lay-direction=\"2\">\n" +
                "                            <i class=\"layui-icon layui-icon-set\"></i>\n" +
                "                            <cite>设置</cite>\n" +
                "                        </a>\n" +
                "                        <dl class=\"layui-nav-child\">\n" +
                "                            <dd class=\"layui-nav-itemed\">\n" +
                "                                <a href=\"javascript:;\">系统设置</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd><a lay-href=\"set/system/website.html\">网站设置</a></dd>\n" +
                "                                    <dd><a lay-href=\"set/system/email.html\">邮件服务</a></dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                            <dd class=\"layui-nav-itemed\">\n" +
                "                                <a href=\"javascript:;\">我的设置</a>\n" +
                "                                <dl class=\"layui-nav-child\">\n" +
                "                                    <dd><a lay-href=\"set/user/info.html\">基本资料</a></dd>\n" +
                "                                    <dd><a lay-href=\"set/user/password.html\">修改密码</a></dd>\n" +
                "                                </dl>\n" +
                "                            </dd>\n" +
                "                        </dl>\n" +
                "                    </li>\n" +
                "                    <li data-name=\"get\" class=\"layui-nav-item\">\n" +
                "                        <a href=\"javascript:;\" lay-href=\"http://www.layui.com/admin/#get\" lay-tips=\"授权\" lay-direction=\"2\">\n" +
                "                            <i class=\"layui-icon layui-icon-auz\"></i>\n" +
                "                            <cite>授权</cite>\n" +
                "                        </a>\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</html>");
        Elements ulEls = document.getElementsByTag("ul");
        ulEls.forEach(ul->{
            Elements liEls = ul.getElementsByTag("li");
            liEls.forEach(li->{
                Elements cite = li.getElementsByTag("cite");
                if (cite != null){
                    System.out.println(
//                            String.format("insert into ratel_singleton.menu (role_id, url, role_icon, parent_id, relation_code, menu_name) values (1, '', 'icon', 0, 1, '%s');",cite.get(0).html())
                    );
                    Elements ddEls = li.getElementsByTag("dd");
                    ddEls.forEach(dd->{
                        Elements aEls = dd.getElementsByTag("a");
                        aEls.forEach(a->{

                            System.out.println(String.format("insert into ratel_singleton.menu (role_id, url, role_icon, parent_id, relation_code, menu_name) values (1, '%s', 'icon', 0, 1, '%s');",a.attr("lay-href"),a.html()));
                        });
                    });
                }
            });

        });
    }

}
