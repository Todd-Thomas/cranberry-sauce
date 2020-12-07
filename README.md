# Action and Stats library
A simple Java library that accepts action names, with times and can return the statistics
based on the average length for each action.

## How to Install Required Dependencies
Since the requirements allowed for any language and stated that the application needed to run on Linux
or macOS, I am making the assumption that this is a greenfield environment. Thus no dependencies have 
been installed, especially JDK8 and Gradle.

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
1.  Install the JDK based on your environment:
    - For macOS see [this](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/macos-install.html)
    - For Linux see [this](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/generic-linux-install.html)

## How to Compile and Build Code

# TODO -TTH- Instructions using Maven

# TODO -TTH- Windows Instructions

## How to Run the Application
# TODO -TTH- Create a sample app that uses the JAR and calls the the functions based on a filename that has input
data


## How to Run the Tests
The tests can easily be run using Gradle. The output will display the test method, test class, and result
of each test. 

# TODO -TTH- add command

Additionally, you can use your browser to open a static web page that illustrates the results as well. It is
located in the current working directory under `build\reports\test\index.html`.

Finally, there is also a JaCoCo code coverage web page report found under `build\reports\jacoco\test\html\index.html`.

