# README

## Install

### Windows

<https://developers.google.com/optimization/install/java/windows>

- Extract `or-tools_VisualStudio2019-64bit_v7.6.7691.zip` in `C:\a\software\or-tools_VisualStudio2019-64bit_v7.6.7691`
- Extract `or-tools_flatzinc_VisualStudio2019-64bit_v7.6.7691.zip` in `C:\a\software\or-tools_flatzinc_VisualStudio2019-64bit_v7.6.7691`

```bash
cd C:\path\to\local\project

mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile="%cd%/lib/com.google.ortools.jar" -DgroupId="com.google" -DartifactId=ortools -Dversion="1.0.0" -Dpackaging=jar -DlocalRepositoryPath="%cd%/local-maven-repo"

mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile="%cd%/lib/protobuf.jar" -DgroupId="com.google" -DartifactId=protobuf -Dversion="1.0.0" -Dpackaging=jar -DlocalRepositoryPath="%cd%/local-maven-repo"
```

### Linux
