def checkout(){
 stage("checkout"){
  node("master"){
   dir(env.svc_name){
    git branch:env.svc_branch ,url: "git@github.com:saikumarmadagoni/"+env.svc_name+".git" ,  credentialsId: "madagonitoken"
   }
  }
 }
}

def sonarscan(){
 stage("sonarscan"){

   withSonarQubeEnv('My SonarQube Server') {
                 sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=spring-svc -Dsonar.projectName='spring-svc' -Dsonar.host.url=http://localhost:9000'
              }


 }

}

def build(){


}
def deploy(){



}
def qadeploy(){


}

return this
