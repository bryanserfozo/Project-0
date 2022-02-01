package com.revature;

import com.revature.controllers.*;
import com.revature.utils.LoggingUtil;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinApp {

    private final PersonController personController = new PersonController();
    private final LoggingUtil loggingUtil = new LoggingUtil();
    private final AccountController accountController = new AccountController();
    private final AppExceptionHandler appExceptionHandler = new AppExceptionHandler();
    private final AuthController authController = new AuthController();
    private final PendingAccountController pendingAccountController = new PendingAccountController();
    private final TransactionController transactionController = new TransactionController();

    private Javalin app = Javalin.create().routes(()->{

        before("people",authController::authorizeEmployeeToken);
        path("people",()->{
            get(personController::handleGetAll);

            before("admin",authController::authorizeAdminToken);
            path("admin", ()->{
                post(personController::handleAdminCreate);
                delete(personController::handleDelete);
            });

            before("{username}",authController::authorizeEmployeeToken);
            path("{username}",()->{
                get(accountController::handleGetAllByUser);

                before("information",authController::authorizeEmployeeToken);
                path("information",()->{
                    get(personController::handleGetOne);
                    put(personController::handleUpdatePassword);
                });
            });

        });


        before("accounts",authController::authorizeEmployeeToken);
        path("accounts", ()->{
            get(accountController::handleGetAll);

            before("{accountId}",authController::authorizeEmployeeToken);
            path("{accountId}", ()->{
                get(transactionController::handleGetAllByID);
                post(transactionController::handleEmployeeTransaction);
                delete(accountController::handleDelete);
            });


            before("pending",authController::authorizeEmployeeToken);
            path("pending", ()->{
                get(pendingAccountController::handleGetAll);
                post(pendingAccountController::handleApproval);
            });
        });


        before("user", authController::authorizeCustomerToken);
        path("user",()->{
            before("{username}", authController::authorizeCustomerToken);
            path("{username}",()-> {
                get(accountController::handleGetAllByUser);
                before("apply", authController::authorizeCustomerToken);
                path("apply", () -> {
                    post(pendingAccountController::handleCreate);
                });


                before("{accountID}", authController::authorizeCustomerToken);
                path("{accountId}", () -> {
                    get(transactionController::handleGetAllByID);
                    post(transactionController::handleCustomerTransaction);
                });

                before("information",authController::authorizeCustomerToken);
                path("information",()->{
                    get(personController::handleGetOne);
                    put(personController::handleUpdatePassword);
                });
            });
        });

        path("login", ()->{
            post(authController::authenticateLogin);
        });

        path("register", ()->{
            post(personController::handleCreate);
        });
        before("*",loggingUtil::logRequest);
    }).exception(NumberFormatException.class, appExceptionHandler::handleNumberFormatException);

    public void start(int port){
        app.start(port);
    }
}
