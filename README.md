# Web-Student-Forum
## Web secured application using databases to keep everything tidy and make it functional. 

Aim of this application is to let users (also called students in this case) create topic threads in each subject directory in order to 
ask questions about exercises, provide solutions to them, collect links pointing to lecture notes' images and also to enroll for an exercise 
using system implemented here to make it all fair and easy. There is an USER and ADMIN interface to manage threads, subthreads, users etc. to make 
its sweet & easy.

**Note**: there is a Swagger API documentation available, feel free to clone this repo, run an app, analyze it and modify.

*What has been used creating this Web App:*   
-Spring Boot   
-Spring MVC   
-Spring Security   
-Hibernate   

After registering, the confirmation email is sent with activation link, only if provided email is on allowed emails list (managed by admin user),
thus preventing external users from registering. The aim of it is to make whole forum private and available only for students attending same university.

## **User profile panel**:
Main aim is to provide account management, mainly changing credentials such as login, password, email, etc.  
Whole functionality is described in AuthenticatedController and also visible at Swagger API.    

## **Home panel**:   
Main aim is to provide functionality for user to add new subthreads (also called topics in this case), enroll for an exercise and manage topics
created by him.  
Whole functionality is described in AuthenticatedController and also visible at Swagger API.     

## **Admin panel**: 
Main aim is to provide functionality for admin user to manage everything created by him and also the others users, it is their topics and account credentials.  
Whole functionality is described in AdminController and also visible at Swagger API.   

## **Configuration**   
Application is ready to launch, all that needs to be done is configuring it, putting into application.properties credentials: user, password and url in order to allow database connectivity. Currently used database it MySQL, nevertheless it easily changeable to any other DB, also in memory database such as H2.    

