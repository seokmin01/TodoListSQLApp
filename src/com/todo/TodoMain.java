package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				System.out.println("제목순으로 정렬하였습니다.");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				System.out.println("제목역순으로 정렬하였습니다.");
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				System.out.println("날짜순으로 정렬하였습니다.");
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				isList = true;
				System.out.println("날짜역순으로 정렬하였습니다.");
				break;
				
			case "ls_cate":
				TodoUtil.ls_cate(l);
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				if (keyword.equals(""))
					System.out.println("검색할 키워드를 같이 입력해주세요.");
				else
					TodoUtil.findItem(l, keyword);
				break;
				
			case "find_cate":
				String keyword_cate = sc.nextLine().trim();
				if (keyword_cate.equals(""))
					System.out.println("검색할 키워드를 같이 입력해주세요.");
				else
					TodoUtil.findCateItem(l, keyword_cate);
				break;

			case "exit":
				quit = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;

			default:
				System.out.println("올바른 명령어를 입력해주세요. (help를 입력하면 명령어를 볼 수 있습니다.)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
