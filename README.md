# Action and Stats library
A simple Java library that accepts action names, with times and can return the statistics
based on the average length for each action.

## How to Install Required Dependencies
Since the requirements allowed for any language and stated that the application needed to run on Linux
macOS or Windows. I am making the assumption that this is a greenfield environment. Thus no dependencies have 
been installed, especially JDK8 and Maven.

#### Installing JDK 8
**NOTE:** *Due to new Oracle licencing policies, I am using 
[AWS' Corretto](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html) 
distribution for this application.* 

1. Issue one the following commands to download JDK 8:
    - For macOS
    ```
    wget https://corretto.aws/downloads/latest/amazon-corretto-8-x64-macos-jdk.pkg
    ```
    - For Linux 
    ```
    wget https://corretto.aws/downloads/latest/amazon-corretto-8-x64-linux-jdk.tar.gz
    ```
    - For Windows
      
        Download the `.msi` file from [here](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html)
1.  Install the JDK based on your environment:
    - For macOS see [this](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/macos-install.html)
    - For Linux see [this](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/generic-linux-install.html)
    - For Windows, double click the `.msi` file to start the wizard

#### Installing Maven

##### macOS, Linux, and Windows
- Download the appropriate compressed file from [here](https://maven.apache.org/download.cgi)
- Follow the installation guides for your OS [here](https://maven.apache.org/install.html)

## How to Compile and Build Code
- The following command will build the JAR file located in this director,
under the current working directory `cranberry-sauce/target/cranberry-sauce-1.0-SNAPSHOT.jar`.

    ```
    mvn clean pacakge
    ```

## How to Execute the Code
The code has been built as a built as a library. In order to execute the code, it must be imported
into a project.

A sample unit test has been created to exercise the code against multiple concurrent threads to
illustrate its thread safety. More information is provided in the section labeled [How 
to Run the Tests](#how-to-run-the-tests).


## How to Run the Tests
The tests can easily be ran using Maven. The output will display the test class, the number of tests
that passed, failed, had errors or were skipped. The full test output will be under based at the current
working directory under `cranberry-sauce/target/surefire-reports`.

    
    mvn clean test
    

Finally, there is also a JaCoCo code coverage web page report found under `target/site/jacoco/index.html`.

