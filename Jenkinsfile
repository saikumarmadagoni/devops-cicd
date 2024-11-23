pipeline {
      parameters {
        booleanParam(
          defaultValue: true,
          description: 'This is a boolean parameter',
          name: 'MY_BOOLEAN_PARAM'
        )
      }
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'echo "Building the application"'
                // Add steps to build the application
            }
        }
        stage('Test') {
            steps {
                 input message: 'Want to skip the test stage?', ok: 'Yes',
                  parameters: [booleanParam(name: 'skip_test', defaultValue: false)]
                script {
                   echo params.skip_test
                   echo env.skip_test
                   echo env.MY_BOOLEAN_PARAM
                    if(params.skip_test) {
                        sh 'echo "Testing the application"'
                        return
                    }
                }
                // Add steps to test the application
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "Deploying the application"'
                // Add steps to deploy the application
            }
        }
    }
}
