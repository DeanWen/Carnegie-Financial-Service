# Carnegie-Financial-Service

This is development repositorty for Carnegie Financial Service, connecting AWS EC2 Deployment Instance.  

Database: Amazon Web Service(AWS) RDS MySQL  
Deployment: Amazon Web Service(AWS) EC2  
Architecure: CMU-08600 Java-J2EEE HW9 MVC Architecture  

<pre>
File Structure:
-Packages
  -Controllers
    -Action
    -Controller
    -RegisterAction
  -Model(DAO) CRUD Interfaces Completed: create() read() update() delete()
    -Customer
    -Employee
    -Fund
    -Transaction
    -Fund Price History
    -Position
  -Bean
    -Customer
    -Employee
    -Fund
    -Transaction
    -Fund Price History
    -Position
  -Form
    -RegisterForm
</pre>

### Database
  if you want to access databases, please type follow command in your terminal.Then you will be able to remote AWS RDS MySQL and execute query 
  <pre>
  mysql -h my-east-db-instance.cq6vzlml6j4g.us-east-1.rds.amazonaws.com:3306 -u root -p 12345678
  </pre>
  
  Please note:  
      You are logged in as ROOT, be carefull about your operation in case affect others
  
### How to configure to your local repository? 
Two easy ways 
<pre>
1.Directly import from your Eclipse 
    --File
      --Imoort
        --Git
          --Project from Github
2.Clone the repository from your Github Client
      --Import Exisiting project
</pre>

### How do you know your configuration succeed?
Currenlty I implemented employee register action for testing.   
After you run on your local Tomcat Server,  You will see DAO Test page, once you click "Done" button, "Register Successfully" Message come out. 

Congratulations! You totally did that correctly and start Coding your action and jsp now.

Let's Ace the Task!

Dean W.  
Team 14 Infinity  
Last updated: 1/21/2015
