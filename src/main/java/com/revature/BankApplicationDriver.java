package com.revature;

import com.revature.daos.PersonDao;
import com.revature.daos.PersonDaoImpl;
import com.revature.daos.TransactionDao;
import com.revature.daos.TransactionDaoImpl;
import com.revature.loggingSingleton.LoggingSingleton;
import com.revature.models.Transaction;
import com.revature.utils.ConnectionUtil;

import java.util.ArrayList;
import java.util.List;

public class BankApplicationDriver {

    public static void main(String[] args) {

         JavalinApp app = new JavalinApp();
         app.start(8080);

        LoggingSingleton logger = LoggingSingleton.getLogger();

        logger.setWriteToFile(true);
        logger.setWriteToConsole(false);

    }
}
