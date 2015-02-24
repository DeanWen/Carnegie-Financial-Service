# Carnegie-Financial-Service

This is development repositorty for Carnegie Financial Service, connecting AWS EC2 Deployment Instance.  

Database: Amazon Web Service(AWS) RDS MySQL  
Deployment: Amazon Web Service(AWS) EC2  
Architecure: Java-J2EE MVC Architecture  

<pre>
File Structure:
-Packages
  -Controllers
    -Action
    -Controller
    - etc...
  -Model(DAO) 
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
  mysql -h my-east-db-instance.cq6vzlml6j4g.us-east-1.rds.amazonaws.com
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

Dean W.  
Team 14 Infinity  
Last updated: 1/28/2015
