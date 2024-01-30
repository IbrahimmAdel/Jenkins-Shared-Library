#!usr/bin/env groovy
def call() {
	echo "Building application..."
	sh ' sudo ./gradlew build --stacktrace'	
}

