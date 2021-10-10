package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("< To Do List 명령어들 >");
        System.out.println("add - 할 일 추가");
        System.out.println("del - 할 일 삭제");
        System.out.println("edit - 할 일 수정");
        System.out.println("ls - 전체 목록");
        System.out.println("ls_name_asc - 제목순 정렬");
        System.out.println("ls_name_desc - 제목역순 정렬");
        System.out.println("ls_date - 날짜순 정렬");
        System.out.println("ls_date_desc - 날짜역순 정렬");
        System.out.println("ls_cate - 카테고리 목록 출력");
        System.out.println("find (키워드) - 제목 또는 내용 항목 검색");
        System.out.println("find_cate (키워드) - 카테고리 항목 검색");
        System.out.println("exit - 종료");
    }
    
    public static void prompt()
    {
    	System.out.print("\n명령어를 입력하세요 > ");
    }
}
