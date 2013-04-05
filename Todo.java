import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
* <ul>
* <li> Filename: Todo.java </li>
* <li> version 1.0 </li>
* <li> created 04.12.2013 </li>
* <li> last modified 05.15.2013 </li>
* <li><b>Author</b>: Cameron Steer(@cdsteer) </li>
* <li><i> no copyright </i></li>
* <li> Purpose of program: Quick manipulation of a Todo list from bash. </li>
* <li> Version history: 1.0 </li>
* </ul>
**/
public class Todo {
    public static void main (String[] args) throws IOException {
        
        File tmp = new File("todo.txt");
        tmp.createNewFile();

        String command = "";
        String task = "";
        //System.out.println(args.length);
		if (args.length < 0) {
            useage();
            return;
        }
        if (args.length == 1){
            command = args[0];
        	if (command.equals("view")){
        		view();
        	}else if (command.equals("clear")) {
        		clear();
        	} else {
        		useage();
        		return;
        	}
        }

        if (args.length >= 2){
        	command = args[0];
            task = args[1];
        	if (args.length > 1){
	        	ArrayList<String> newTask = new ArrayList<String>();
	        	for (int i = 2; i < args.length; i++) {
					newTask.add(args[i]);
				}
				task = (task + stringBuilder(newTask));
			}
            if (command.equals("add")){
                addTask(task);
            }else if (command.equals("del")) {
                del(task);
            } else {
                useage();
                return;
            }
        } 
    }

    /**
    *<ul>
    *<li> Sacns todo.txt and crates an ArrayList for the tasks</li>
    *@return ArrayList of tasks from todo.txt
    *</ul>
    **/
    private static ArrayList<String> taskList(){
        ArrayList<String> taskList = new ArrayList<String>();
        Scanner in = null;
        final String FILE = "todo.txt";
        String task = "";
        try{
            in = new Scanner(new File(FILE));
            in.useDelimiter("[,]");
            while (in.hasNext()){
            task = in.next();
            taskList.add(task);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Input file not found.");
            return null;
        }
        finally{
            in.close();
        }
        return(taskList);
    }

    /**
    *<ul>
    *<li> Prints out tasks from ArrayList taskList </li>
    *</ul>
    **/
	private static void view() {
        ArrayList<String> taskList = new ArrayList<String>();
        taskList = taskList();
        String task = "";
        if (isempty() == false){
            for(int i = 0; i <= taskList.size()-1; i++ ){
                task = taskList.get(i);
                System.out.print(task+"\n");
            }
        }else {
            System.out.println("Todo list empty, Yay!");
        }
	}

    /**
    *<ul>
    *<li> New task is added to end of ArrayList Tasklist then runs addList() with the new ArrayList</li>
    *@param value is an String value to be added to the ArrayList.
    *</ul>
    **/
	private static void addTask(String task) throws FileNotFoundException{
        ArrayList<String> taskList = new ArrayList<String>();
        taskList = taskList();
        taskList.add(task+",");
        addList(taskList);      
    }

    /**
    *<ul>
    *<li> Displays how get help for using the program </li>
    *@param ArrayList of the task from Todo.txt
    *</ul>
    **/
    private static void addList(ArrayList<String> taskList) throws FileNotFoundException{
        PrintWriter out = null;
        final String FILE = "todo.txt";
        String task = "";
        try{
            out = new PrintWriter(FILE);
            for(int i = 0; i <= taskList.size()-1; i++){
                task = taskList.get(i);
                out.print(task+",");
            }
            }
        finally {
            if (out != null) {
                out.close();
            }       
        }
        System.out.println("Task Added!");
    }


    /**
    *<ul>
    *<li> Emptys Todo.txt of all tasks </li>
    *</ul>
    **/
	private static void clear() throws FileNotFoundException{
        PrintWriter out = null;
        final String FILE = "todo.txt";
        try{
            out = new PrintWriter(FILE);
            out.print("");
            }
        finally {
            if (out != null) {
                out.close();
            }       
        }
        System.out.println("Todo List Cleared!");
	}

    private static void del(String task){

    }

    /**
    *<ul>
    *<li> Combines an ArrayList of Strings to a single String value </li>
    *@param value is an ArrayList of String values to be combined as one String.
    *@return Returns the string value 
    *</ul>
    **/
	private static String stringBuilder(ArrayList<String> task){
		String result = "";
		for (String s : task) {
		    result += (" "+s);
		}
		return result;
	}

    /**
    *<ul>
    *<li> Cheacks if todo.txt is empty </li>
    *@return true if the named file is empty; false has content
    *</ul>
    **/
    private static boolean isempty() {
        File file = new File("todo.txt");
        if(file.length() == 0){
         return true;
        }else{
         return false;
        }
    }

    /**
    *<ul>
    *<li> Displays how get help for using the program </li>
    *</ul>
    **/
	private static void useage(){
        System.out.println("[useage] java Todo help");
    }
}