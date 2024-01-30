#!usr/bin/env groovy
def call() {
	echo "Building application..."
	sh './gradlew build --stacktrace'	
}

