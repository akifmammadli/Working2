package prac;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class  Working2 {
static ArrayList<String> words = new ArrayList<String>();
static int counter =0;

static String url = "jdbc:mysql://localhost:3306/file";
static int count; //
static StringBuffer word;
static Connection con; //
static ResultSet rs; //
static PreparedStatement preparedStatement;

        static void printPermutn(String str, String ans)  {

        if (str.length() == 0) {
        if(!words.contains(ans)) { words.add(ans);
        }

        counter ++;
        return;
        }

        for (int i = 0; i < str.length(); i++) {

        char ch = str.charAt(i);

        String ros = str.substring(0, i) +
        str.substring(i + 1);

        printPermutn(ros, ans + ch);

        }
        }

        static void reduceStr(String str) {

        if (str.length() == 1 ) {
        return;
        }

        String newWord;
        for (int j = 0; j < str.length(); j++) {

        newWord = str.substring(0,j)+str.substring(j+1);
        printPermutn(newWord, "");
        reduceStr (newWord);
        }
        }

        public static void verifyWords() {

        System.out.println(words.size());
        System.out.println("test");

        for (int i = 0; i< words.size(); i++) {
        try {
        con = DriverManager.getConnection(url, "root", "1234");
        preparedStatement = con.prepareStatement("SELECT * FROM DICT WHERE word=?");
        preparedStatement.setString(1, words.get(i));
        rs = preparedStatement.executeQuery();
        rs = preparedStatement.getResultSet();
        if (rs.next()) {
        count++;
        System.out.println(words.get(i));
        }
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
        }

        System.out.println(count);

        }

        public static void main(String[] args)
        {
        String s = "working";

        reduceStr(s);
        verifyWords();

        }
        }