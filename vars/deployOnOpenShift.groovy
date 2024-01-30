#!/usr/bin/env groovy
//def call(String imageName, String deploymentFileName, String openshifProject, String openshiftCluster){ //String imageName, String deploymentFileName, String openshiftCredentialsID, String openshiftServerURL, String openshifProject) {
	// Update deployment.yaml with new Docker Hub image
  //      sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' ${deploymentFileName}"
                    
        // Log in to OpenShift with serviceaccount
//	sh "oc apply -f . -n ${openshiftProject} --server=${openshiftCluster}"


	
	//withCredentials([string(credentialsId: "${openshiftCredentialsID}", variable: 'OPENSHIFT_TOKEN')]) {
          //      sh "oc login --token=${OPENSHIFT_TOKEN} --server=${openshiftServerURL} --insecure-skip-tls-verify"
        //	echo "Deploying the APP to OpenShift..."
        //	sh "oc apply -f . -n ${openshifProject}"
        //}      
//}

//#!usr/bin/env groovy
//def call(String imageName, String deploymentFileName, String openshiftProject, String openshiftCluster) {
 //   // Update deployment.yaml with new Docker Hub image
  //  sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' ${deploymentFileName}"
                    
    // Log in to OpenShift with serviceaccount
   // sh "oc apply -f . -n ${openshiftProject} --server=${openshiftCluster}"
//}
def call(String imageName ,String deploymentFileName ,String openshiftProject ,String openshiftCluster) {
	sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' ${deploymentFileName}"
	openshift.withCluster(openshiftCluster) {
  		openshift.withProject(openshiftProject) {
			sh "oc apply -f ." //-n ${openshiftProject} --server=${openshiftCluster}"	
		}
	}
}




