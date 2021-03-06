node {
    stage ('Checkout') {
       checkout scm
    }

    stage ('Build and Static Analysis') {
        
         sh 'mvn -V -e clean verify -Dmaven.test.failure.ignore -Dgpg.skip'
       
        junit 'target/**/TEST-*.xml'

        recordIssues tools: [java(), javaDoc()], aggregatingResults: 'true', id: 'java', name: 'Java'
        recordIssues tool: errorProne(), healthy: 1, unhealthy: 20

        recordIssues tools: [checkStyle(pattern: 'target/checkstyle-result.xml'),
            spotBugs(pattern: 'target/spotbugsXml.xml'),
            pmdParser(pattern: 'target/pmd.xml'),
            cpd(pattern: 'target/cpd.xml'),
            taskScanner(highTags:'FIXME', normalTags:'TODO', includePattern: '**/*.java', excludePattern: 'target/**/*')],
            qualityGates: [[threshold: 1, type: 'TOTAL', unstable: true]]
    }

    stage ('Collect Maven Warnings') {
        recordIssues tool: mavenConsole()
    }
}
