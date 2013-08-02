TeamCity runner for tSQLt
=====

[tSQLt](http://tsqlt.org) is a Unit Test Framework for TSQL (the SQL dialect in SQL Server). 

Most solutions to run tSQLt with TeamCity involves custom SQL and Powershell scripts and even RedGate has a tool for that (and a plugin for [TeamCity](http://www.jetbrains.com/teamcity/) as well). I needed something ever simpler, that can run tests already present in a database and report the results back. This is the result of that requirement.

Installation
---

* Get the zip file
* Stop your TeamCity server
* Place the zip file in the plugin directory of your TeamCity installation (check TeamCity documentation for details)
* Start your TeamCity server
* Configure and enjoy!


Notes about configuration
---
* Windows Single Sign-On authentication is not supported yet, I will try to implement that in a next release
* If you don't specify the port, default (1433) would be used.
* If using Windows Authentication instead of SQL Server Authentication you should specify the domain or machine name to log-in (using the notation domain\user).
* The runner doesn't prepare your database for testing, the recommended way is to have separate steps for creation/prepare the database before the tSQLt runner step and drop the database at the end. Believe me, you would thank me later for that advice.
* tSQLt is supported only in SQL Server 2005 and greater (You should check tSQLt documentation if you are using this plugin anyway).