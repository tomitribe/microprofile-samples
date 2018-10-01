# Fault tolerance samples

This is a project to try out TomEE 7.1.0 Fault Tolerance v1.0 features.

For more details, please check the 
[Fault Tolerance spec page](https://microprofile.io/project/eclipse/microprofile-fault-tolerance).

### Build and deploy

To build and execute all the test:

`mvn clean install 
`

These samples can be executed in 3 different ways:
 
* using the Arquillian tests inside each sub-project. You can use your IDE to run each of the examples. 
Check for the `MovieResourceTest` classes.

* You can simply deploy the WAR by going to a subproject and execute the TomEE maven plugin:

`cd timeout`

`mvn clean package -DskipTests tomee:run `

* Running a single fat jar that bundles the app and TomEE. For this you need to first build the project 
if the fat jar is not present in the `timeout/target` folder:

`cd timeout` 

`mvn clean package -DskipTests`

`java -jar target/retry-1.0-SNAPSHOT-exec.jar`

### Execute

After deploying the example app you can call the movies endpoint on your browser. Check the logs for the precise path:

[http://localhost:8080/timeout-1.0-SNAPSHOT/movies/](http://localhost:8080/timeout-1.0-SNAPSHOT/movies/)

This will throw you an HTTP 500 error and on the cause you can see the `java.util.concurrent.TimeoutException`.

You can follow a similar procedure in most of the other sub-projects and get a better feeling on the outcome 
of each Fault Tolerance pattern.

**TIP:**

Try to change the default attributes on the annotations to fine tune the behaviour.