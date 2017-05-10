## Requirements

- JRE 8. Download here if you don't have it https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

## How to run application

- `cd` into the directory where you extracted the code and executable and choose any of following run options. mini braintree accept input from two sources:
  1. a filename passed in command line arguments. e.g.

            $./mini-braintree input.txt

  2. or a filename passed from STDIN e.g.

            $./mini-braintree < input.txt

- I have included `input.txt` in the source code. Feel free to edit it to test different input cases.
- Output will be displayed on STDOUT after all input is read and processed.
- `Ctrl+C` to kill the running application.

## How to run tests

- `cd` into the directory where you extracted the code and run the bash executable to run entire test suite. e.g.

        $./mini-braintree-test
    
## Design decisions

1. After reading the requirements and the list of input, output; I realized that there are three main resources
    * User
    * Card

    The resources are represented by there own classes in resources package.
2. Each resource gets their own service class where the application logic/ business logic is handled.
Braintree is implemented as an orchestrator that uses other services. All services are found in service package
3. Validators are run before creating and storing records in database. They are under validator package.
4. Database is in memory which does not persist data after the application is closed.
Users are stored in TreeMap to maintain alphabetical order and Cards are stored in simply HashMap.
5. All commands and errors are available as string constants in constants package.
6. All tests are written in JUnit. Tests are included in tests package.
7. Scripts `mini-braintree` and `mini-braintree-test` simply run `braintree.jar` and `braintree-test.jar` respectively.

## Choice of Language : Java
Java runs on almost all environments. I used `java 8` for this project. I chose java because of I feel comfortable programming in java. It is a strongly typed, forces me to make less syntax errors and it offers object oriented, procedural as well as functional programming styles which gives me more flexibility as a programmer.
