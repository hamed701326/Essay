import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.core.share.AuthenticationService;
import ir.science.essay.entities.Article;
import ir.science.essay.entities.Category;
import ir.science.essay.entities.User;
import ir.science.essay.essayfeature.impl.*;
import ir.science.essay.essayfeature.usecase.*;
import ir.science.essay.logfeature.impl.LogInUseCaseImpl;
import ir.science.essay.logfeature.impl.LogUpUseCaseImpl;
import ir.science.essay.logfeature.usecase.LogInUseCase;
import ir.science.essay.logfeature.usecase.LogUpUseCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.Scanner;

public class MainApp {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {

        int mainOption=1;
        while (mainOption!=4)
        System.out.print("1.Sign in\n\t2.Sign up\n\t3.ShowAllEssays\n\t4.Exit\nOption:");
        mainOption=scanner.nextInt();
        switch (mainOption){
            case 1:
                signIn();
                break;
            case 2:
                signUp();
                break;
            case 3:
                showAllEssays();
                break;

        }
    }

    private static void showAllEssays() {
        FindAllEssayUseCase findAllEssay=new FindAllEssayUseCaseImpl();
        findAllEssay.get().forEach(System.out::println);

    }

    private static void signUp() {
        LogUpUseCase logUp=new LogUpUseCaseImpl();
        logUp.createAccount(setUser());
    }

    private static void signIn() {
        LogInUseCase logIn=new LogInUseCaseImpl();
        System.out.print("UserName:");
        String userName=scanner.next();
        System.out.print("PassWord:");
        String password=scanner.next();
        User user=logIn.login(userName,password);
        if(user!=null){
        AuthenticationService authenticationService=AuthenticationService.getInstance();
             authenticationService.setLoginUser(user);
        int userOption=1;
        while(userOption!=4) {
            System.out.print("1.Show my essays\n\t2.Edit essays\n\t3.insert\n\t4.Exit\nOption:");
            userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    FindEssayByUserUseCase findMyEssay = new FindEssayByUserUseCaseImpl();
                    findMyEssay.get().forEach(System.out::println);
                    break;
                case 2:
                    //edit:1. edit information 2. edit publish state;
                    edit(user);
                    break;
                case 3:
                    InsertEssayByUserUseCase insertEssay =
                            new InsertEssayByUserUseCaseImplUseCase();
                    insertEssay.set(setEssay(user));
            }
        }
        }
    }
    public  static User setUser(){
        User user=new User();
        System.out.print("User Name: ");
        user.setUserName(scanner.next());
        System.out.println("Password: ");
        user.setPassword(scanner.next());
        System.out.print("Date[years/month/day]:");
        String str=scanner.next();
        String [] date=str.split("/");
        user.setBirthday(setDate(Long.parseLong(date[0]),
                Long.parseLong(date[1]),
                Long.parseLong(date[2])));
        System.out.print("National_Id:");
        user.setNationalCode(scanner.next());
        return user;
    }

    private static void edit(User user) {
        int editOption=1;
        while(editOption!=3){
            System.out.print("\n\t1.edit information\n\t2.edit publish state" +
                    "\n\t3.exit" +
                    "\noption:");
            editOption=scanner.nextInt();
            EditEssayByUserUseCase editEssay=new EditEssayByUserUseCaseImpl();
            FindEssayByUserUseCase findEssay=new FindEssayByUserUseCaseImpl();

            System.out.print("Enter Id:");
            int id=scanner.nextInt();
            Article article=findEssay.get(id);
            if( article==null) {
                switch (editOption) {
                    case 1:

                        editEssay.editInformation(setEssay(user), id);
                        break;
                    case 2:

                        editEssay.editPublish(id);
                }
            }else
                System.out.printf("There isn't any article with this id=%d\n",id);
        }
    }

    private static Article setEssay(User user) {
        FindCategoryByUserUseCase findCategory=new FindCategoryByUserUseCaseImpl();
        ShowCategoryUseCase showCategory=new ShowCategoryUseCaseImpl();
        showCategory.get().forEach(System.out::println);
        System.out.println("\n\t1.there isn't my category.\n\t2.Enter Category Id:");
        int categoryOption=scanner.nextInt();
        if(categoryOption==1){
            System.out.println("enter new Category:");
            categoryOption=setCategory();
        }

        Category category=findCategory.get(categoryOption);
        System.out.print("Title:");
        String title=scanner.next();
        System.out.print("Brief:");
        String brief=scanner.next();
        System.out.print("content:");
        String content=scanner.next();

        return new Article(title,brief,content,new Date(),new Date(),
                null,false,user,category);
    }

    private static int setCategory() {
        InsertCategoryByUserUseCase insertCategory=
                new InsertCategoryByUserCaseImpl();
        System.out.print("title:");
        String title=scanner.next();
        System.out.print("description:");
        String description=scanner.next();
        int id=insertCategory.set(new Category(title,description));
        return id;
    }

    public static Date setDate(Long years,Long month,Long days){
        long timeStamp=((years-1970)*365+(month-1)*30+(days-1))*24*3600*1000;
        return new Date(timeStamp);
    }
}
