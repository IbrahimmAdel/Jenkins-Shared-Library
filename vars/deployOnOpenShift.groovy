#!/usr/bin/env groovy
//def call(String openshiftCluster, String openshiftProject, String imageName) {
	
	// Update deployment.yaml with new Docker Hub image
//	sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' deployment.yaml"

	// Login and Deploy on OpenShif cluster
//	openshift.withCluster(clustername: "${openshiftCluster}" ,serverurlVariable: 'serverURL', tokenVariable: 'token') {
//		openshift.withProject("${openshiftProject}") {
//			sh "oc login --server=${serverURL} --token=${token} --insecure-skip-tls-verify"
//			sh "oc project "${openshiftProject}""
//			sh "oc apply -f ." 
//		}
//	}
//}


def call(String openshiftProject, String imageName) {
    // Update deployment.yaml with new Docker Hub image
    sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' deployment.yaml"

    // Retrieve OpenShift credentials from Jenkins credentials store
    withCredentials([openshiftServiceAccount(credentialsId: 'OpenShift', variable: 'openshiftCredentials')]) {
        // Login and Deploy on OpenShift cluster
        openshift.withCluster(
            cluster: 'OpenShift',
            serverUrlVariable: 'serverURL', 
            credentialsId: '${openshiftCredentials}'
        ) {
            openshift.withProject("${openshiftProject}") {
                script {
                    // Use the 'serverURL' variable here
                    sh "oc login --token=${openshiftCredentials} --insecure-skip-tls-verify"
                    sh "oc project ${openshiftProject}"
                    sh "oc apply -f ."
                }
            }
        }
    }
}

