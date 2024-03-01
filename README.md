## Getting Started

- Compile:
javac --source-path src -d bin src/sg/edu/nus/iss/sdf/App.java src/sg/edu/nus/iss/sdf/FileService.java
- Run:
java -cp bin sg.edu.nus.iss.sdf.App

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Current Assumptions
records entered are always 50 columns