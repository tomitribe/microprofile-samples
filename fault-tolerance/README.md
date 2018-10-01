# Fault tolerance samples

This is a project to try out TomEE 7.1.0 Fault Tolerance v1.0 features.

For more details, please check the 
[Fault Tolerance spec page](https://microprofile.io/project/eclipse/microprofile-fault-tolerance).

### Build and test

To build and execute all the test:

`mvn clean install 
`

These samples can be executed in 2 ways, using the Arquillian tests 
inside each sub-project or by deploying and using the WAR file created by the build.

You can simply deploy the WAR by going to a subproject and execute the TomEE maven plugin:

`cd timeout`

`mvn clean package -DskipTests tomee:run `

Later you can call the movies endpoint on your browser. Check the logs for the precise path:

[http://localhost:8080/timeout-1.0-SNAPSHOT/movies/](http://localhost:8080/timeout-1.0-SNAPSHOT/movies/)

This will throw you an HTTP 500 error and on the cause you can see the `java.util.concurrent.TimeoutException`.


You can follow a similar procedure in most of the other sub-projects and get a better feeling on the outcome 
of each Fault Tolerance pattern.

**TIP:**

Try to change the default attributes on the annotations to fine tune the behaviour.