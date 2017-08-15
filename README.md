# checkoutkata
checkoutkata is a console application that validates the sku's checkout by the customer

GitHub project repository: https://github.com/kiranyenigala/checkoutkata.git

source tree

Project's source tree looks like:


.
|-- nbproject
`-- src
    |-- main
    |   `-- java
    |       `-- com
    |           `-- itv
    |               `-- checkout
    |                    `-- model
    |                    `-- service
	|                    `-- validator  
    |
    `-- test
        `-- java
            `-- com
                `-- itv
                    `-- checkout
                        `-- model
                         `-- service
			 `-- validator
			 

dependencies

checkoutkata depends on just few popular libraries/frameworks:

junit - for testing.
mockito - for testing
java 8 - testing and development

running the application

The simplest way to run the application is to clone the repository, and use Maven to compile and run tests:

cd /tmp
git clone git@github.com:kiranyenigala/checkoutkata.git
cd checkoutkata
mvn compile

Testing

To execute tests run:

mvn test
