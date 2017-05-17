package com.lhd.util;

/**
 * Created by lhd on 2017/5/14.
 */
public class PageUtils {

    /**
     * 获取分页html
     * @param currentPageNo 当前页面
     * @param action 执行action
     * @param maxPageNo 最大页数
     * @return
     */
    public static String getPagination(int currentPageNo,String action,int maxPageNo){

        StringBuffer sb = new StringBuffer();
        sb.append("<nav class=\"paging\">");
        if(currentPageNo != 1){
            sb.append(" <a class=\"extend prev\" rel=\"prev\" href=\"/"+action+"?pageNo="+(currentPageNo-1)+"\">\n" +
                    "\t\t<div class=\"prev-text\"><i class=\"material-icons\">chevron_left</i></div>\n" +
                    "\t</a>");
        }
        sb.append("<span class=\"page-number current\">"+currentPageNo+"</span>");
        if(currentPageNo != maxPageNo){
            sb.append("<a class=\"extend next\" rel=\"next\" href=\"/"+action+"?pageNo="+(currentPageNo+1)+"\">\n" +
                    "\t\t<div class=\"next-text\">\n" +
                    "\t\t\t<i class=\"material-icons\">chevron_right</i>\n" +
                    "\t\t</div>\n" +
                    "\t</a>");
        }
        sb.append("</nav>");
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(getPagination(2,"",3));
    }

}
