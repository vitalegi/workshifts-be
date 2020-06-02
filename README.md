# Getting Started

## Setup

This project is based on OrTools optimization engine. Download required software from <https://developers.google.com/optimization/install>

Install software in local repository

```bash
cd /path/to/project/folder

mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file `
-Dfile="./lib/com.google.ortools.jar" `
    -DgroupId="com.google" -DartifactId=ortools `
    -Dversion="1.0.0" -Dpackaging=jar `
    -DlocalRepositoryPath="./local-maven-repo"

mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file `
-Dfile="./lib/protobuf.jar" `
    -DgroupId="com.google" -DartifactId=protobuf `
    -Dversion="1.0.0" -Dpackaging=jar `
    -DlocalRepositoryPath="./local-maven-repo"
```

## Build

```
set PATH=C:\a\software\apache-maven-3.5.0\bin;C:\Program Files\Java\jdk1.8.0_131\bin;%PATH%
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_131
mvn clean package
```

## Run

```
set PATH=C:\Program Files\Java\jdk1.8.0_131\bin;%PATH%
java -jar .\target\workshifts-${project.version}.jar <OPTIONS>
```
