package com.revature;

import com.revature.daos.PersonDao;
import com.revature.daos.PersonDaoImpl;
import com.revature.utils.ConnectionUtil;

public class BankApplicationDriver {

    public static void main(String[] args) {

         JavalinApp app = new JavalinApp();
         app.start(8080);
    }
}
