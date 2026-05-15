# Excel Chat — Setup Guide

A simple Spring Boot application. Follow the steps below to run it on your machine.

## Prerequisites

### 1. Install VS Code

Download and install Visual Studio Code from:
https://code.visualstudio.com/

### 2. Install Java 17

Download and install JDK 17 from either:
- https://adoptium.net/ (recommended)
- https://www.oracle.com/java/technologies/downloads/

### 3. Set the `JAVA_HOME` environment variable

**On Windows:**
1. Press `Win + S`, search for *Environment Variables*, open it
2. Under *System variables*, click **New**
3. Variable name: `JAVA_HOME`
4. Variable value: path to your JDK install (e.g. `C:\Program Files\Eclipse Adoptium\jdk-17`)
5. Edit the `Path` variable and add: `%JAVA_HOME%\bin`
6. Open a new terminal and verify:
   ```
   java -version
   ```

**On macOS / Linux:**

Add to your `~/.zshrc` or `~/.bashrc`:
```
export JAVA_HOME=/path/to/your/jdk-17
export PATH=$JAVA_HOME/bin:$PATH
```
Then reload:
```
source ~/.zshrc
```
Verify:
```
java -version
```

You should see something like `openjdk version "17.x.x"`.

## Running the Project

### 1. Open the project in VS Code

- Open VS Code
- `File` → `Open Folder` → select this project's folder

### 2. Open a terminal in VS Code

- `Terminal` → `New Terminal` (or `Ctrl + ~`)

### 3. Run the application

**On Windows:**
```
.\mvnw spring-boot:run
```

**On macOS / Linux:**
```
./mvnw spring-boot:run
```

The first run will download dependencies — give it a minute.

### 4. Open the app

Once you see `Started ... in X seconds` in the terminal, visit:

http://localhost:8080

## Stopping the App

Press `Ctrl + C` in the terminal.

## Troubleshooting

- **`java: command not found`** — Java isn't on your PATH. Revisit the `JAVA_HOME` step.
- **`Port 8080 already in use`** — another app is using port 8080. Stop it, or change the port in `src/main/resources/application.properties` by adding `server.port=8081`.
- **`mvnw` permission denied (macOS / Linux)** — run `chmod +x mvnw` once, then retry.
