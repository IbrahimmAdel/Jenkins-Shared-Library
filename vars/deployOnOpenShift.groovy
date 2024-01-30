#!usr/bin/env groovy
def call(String imageName, String deploymentFileName, String openshiftCredentialsID, String openshiftServerURL, String openshifProject) {
	// Update deployment.yaml with new Docker Hub image
        sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' ${deploymentFileName}"
                    
        // Log in to OpenShift with serviceaccount
	withCredentials([string(credentialsId: "${openshiftCredentialsID}", variable: 'OPENSHIFT_TOKEN')]) {
                sh "oc login --token=${OPENSHIFT_TOKEN} --server=${openshiftServerURL} --insecure-skip-tls-verify"
        	echo "Deploying the APP to OpenShift..."
        	sh "oc apply -f . -n ${openshifProject}"
        }      
}
