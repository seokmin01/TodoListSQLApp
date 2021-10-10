package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TodoUtil {

	public static void createItem(TodoList l) {

		String title, category, desc, due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print("\n" + "[할 일 추가]\n" + "제목 > ");

		title = sc.nextLine().trim();
		if (l.isDuplicate(title)) {
			System.out.printf("중복되는 제목입니다.");
			return;
		}

		System.out.print("카테고리 > ");
		category = sc.nextLine().trim();

		System.out.print("내용 > ");
		desc = sc.nextLine().trim();

		System.out.print("마감일자 (yyyy/mm/dd) > ");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, category, desc, due_date);
		if (l.addItem(t) > 0)
			System.out.println("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {

		int i = 1;
		int n;
		String chk;

		Scanner sc = new Scanner(System.in);

		System.out.print("\n" + "[할 일 삭제]\n" + "삭제할 할 일의 번호 > ");

		n = sc.nextInt();

		if (l.deleteItem(n) > 0)
			System.out.println("삭제되었습니다.");
	}

	public static void updateItem(TodoList l) {

		int i = 1;
		int n;
		Scanner sc = new Scanner(System.in);

		System.out.print("\n" + "[할 일 수정]\n" + "수정할 할 일의 번호 > ");

		n = sc.nextInt();

		sc.nextLine();
		System.out.print("새로운 제목 > ");
		String new_title = sc.nextLine().trim();

		System.out.print("새로운 카테고리 > ");
		String new_category = sc.nextLine().trim();

		System.out.print("새로운 내용 > ");
		String new_description = sc.nextLine().trim();

		System.out.print("새로운 마감일자 > ");
		String new_due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(new_title, new_category, new_description, new_due_date);
		t.setId(n);
		if (l.updateItem(t) > 0)
			System.out.println("수정되었습니다.");

	}

	public static void completeItem(TodoList l, int id) {
		if (l.completeItem(id)) {
			System.out.println("완료한 할 일로 변경되었습니다.");
		} else {
			System.out.println("잘못된 번호입니다.");
		}
	}

	public static void completeList(TodoList l) {
		int cnt = 0;
		for (TodoItem item : l.completeList()) {
			System.out.println(item.getId() + item.toString());
			cnt++;
		}
		System.out.println("총 " + cnt + "개의 항목을 찾았습니다.");
	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록, 총 " + l.getCount() + "개]");

		for (TodoItem item : l.getList()) {
			System.out.println(item.getId() + item.toString());
		}
	}

	public static void listAll(TodoList l, String order, int n) {
		System.out.println("[전체 목록, 총 " + l.getCount() + "개]");

		for (TodoItem item : l.getOrderedList(order, n)) {
			System.out.println(item.getId() + item.toString());
		}
	}

	public static void saveList(TodoList l, String fileName) {
		try {
			Writer w = new FileWriter(fileName);

			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();

			System.out.println("모든 할 일 목록이 저장되었습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadList(TodoList l, String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			int count = 0;
			String todoLine;
			while ((todoLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(todoLine, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();

				TodoItem t = new TodoItem(title, category, desc, due_date, current_date);
				l.addItem(t);
				count++;
			}
			br.close();
			System.out.println(count + "개의 항목을 읽었습니다.");
		} catch (FileNotFoundException e) {
			System.out.println(fileName + "라는 파일이 존재하지 않습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void findItem(TodoList l, String keyword) {

		int i = 1, cnt = 0;
		Scanner sc = new Scanner(System.in);

		for (TodoItem item : l.getList()) {
			if (item.getTitle().contains(keyword) || item.getDesc().contains(keyword)) {
				System.out.println(i + ". " + item.toString());
				cnt++;
			}
			i++;
		}

		if (cnt == 0)
			System.out.println("검색된 항목이 없습니다.");
		else
			System.out.println("총 " + cnt + "개의 항목을 찾았습니다.");
	}

	public static void findCateItem(TodoList l, String keyword) {

		int i = 1, cnt = 0;
		Scanner sc = new Scanner(System.in);

		for (TodoItem item : l.getList()) {
			if (item.getCategory().contains(keyword)) {
				System.out.println(i + ". " + item.toString());
				cnt++;
			}
			i++;
		}

		if (cnt == 0)
			System.out.println("검색된 항목이 없습니다.");
		else
			System.out.println("총 " + cnt + "개의 항목을 찾았습니다.");
	}

	public static void ls_cate(TodoList l) {
		int cnt = 0;
		String str = "";
		for (TodoItem item : l.getList()) {

			if (cnt == 0) {
				str = item.getCategory();
				cnt++;
			}

			if (!str.contains(item.getCategory())) {
				str += " / " + item.getCategory();
				cnt++;
			}
		}
		System.out.println(str);
		System.out.println("총 " + cnt + "개의 카테고리가 등록되어 있습니다.");
	}

}
