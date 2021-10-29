# Proiect Colectiv G4 - 2021

The **Proiect Colectiv G4 - 2021** repository hosts the REST API and UI code base for the iChef Web Application developed by The Chefs.

## Notes

* Documentation for the project is kept under the following directory: ***Planning***
* All commands reffering to "Frontend" need to be ran from the following directory: ***iChef-WebUi***
* All commands reffering to "Backend" need to be ran from the following directory: ***iChef-WebApi***

## Development

### Prerequisites

* Clone this repository
* For Frontend:
  * Install NPM and Node from the [official site](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm).
  * Install the Angular CLI from the [official site](https://angular.io/cli).
  * Install your IDE of choice for JS and HTML. Recommendation: [VSCode](https://code.visualstudio.com/).
* For Backend:
  * Install Java JDK 8 from the [official site](https://treehouse.github.io/installation-guides/windows/jdk-windows.html). The SDK can also be downloaded through IntelliJ.
  * Install your IDE of choice for Java. Recommendation: [IntelliJ](https://www.jetbrains.com/idea/).

### Build

* Frontend:
  * Fetch all dependencies:

  ```bash
  npm install
  ```

  * Compile the application:

  ```bash
  ng build
  ```

* Backend:
  * Compile the application:

  ```bash
  mvn -B -DskipTests package --file pom.xml
  ```

### Test

*Note: If anything fails, try to investigate why that happens before beginning development.*

* Frontend:
  * Run tests

  ```bash
  npm run test
  ```

* Backend:
  * Run tests

  ```bash
  mvn test
  ```

## Run & Deploy

Note that you have to build the app first before running any of the following commands.

* Run Angular application locally

  Simply run the following script to start the webserver:

  ```bash
  ng serve -o
  ```

* Run Java application locally

  Simply run the following script to start the web api:

  ```bash
  mvn exec:java <investigate this to make sure it is the right command>
  ```
  