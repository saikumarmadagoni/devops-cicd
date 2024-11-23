pipeline {
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
     booleanParam(name: 'skip_test', defaultValue: false, description: 'Set to true to skip the test stage')
                  parameters: { [booleanParam(name: 'skip_test', defaultValue: false)] }
                script {
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
