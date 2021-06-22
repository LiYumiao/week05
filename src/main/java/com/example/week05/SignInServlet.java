package com.example.week05;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@WebServlet(name = "SignInServlet", value = "/sign-in")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("sign-in.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("inputName");
        String password = req.getParameter("inputPassword");
        try {
            FileReader read = new FileReader("C:\\Users\\LiYumiao\\Desktop\\name password.txt");
            BufferedReader br = new BufferedReader(read);
            String string;
            int flag = 0;
            while ((string = br.readLine()) != null) {
                String s[] = string.split("\\s", 3);
                if (name.equals(s[0])) {
                    flag++;
                    if (password.equals(s[1])) {
                        req.getSession().setAttribute("user", name);
                        resp.sendRedirect("index");
                    }
                    else {
                        req.getRequestDispatcher("WrongPassword.html").forward(req, resp);
                    }
                }
            }
            read.close();
            br.close();
            if (flag == 0){
                req.getRequestDispatcher("NoSuchUser.html").forward(req, resp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
