#!usr/bin/env groovy
def call(String sonarqubeServerName, String sonarqubeAuthenticationToken, String sonarqubeProjectKey, String sonarqubeHostServer) {
	withSonarQubeEnv("${sonarqubeServerName}") {
	        withCredentials([string(credentialsId: "${sonarqubeAuthenticationToken}", variable: 'token')]) {
        		echo "Running SonarQube Analysis..."
        		sh "sudo ./gradlew sonar \
        		    -Dsonar.projectKey=${sonarqubeProjectKey} \
        		    -Dsonar.host.url='http://${sonarqubeHostServer}:9000' \
        		    -Dsonar.login=${token}"       			    
		}
	}
}
