node {
    stage ('Checkout') {
        git branch:'master', url: 'https://github.com/uhafner/codingstyle.git'
    }

    stage ('Build and Static Analysis') {
        withMaven {
            sh 'mvn -V -e clean verify -Dmaven.test.failure.ignore -Dgpg.skip'
        }

        recordIssues tools: [java(), javaDoc()], aggregatingResults: 'true', id: 'java', name: 'Java'
        recordIssues tool: errorProne(), healthy: 1, unhealthy: 20

        junit testResults: '**/target/*-reports/TEST-*.xml'

        recordIssues tools: [checkStyle(pattern: 'target/checkstyle-result.xml'),
            spotBugs(pattern: 'target/spotbugsXml.xml'),
            pmdParser(pattern: 'target/pmd.xml'),
            cpd(pattern: 'target/cpd.xml'),
            taskScanner(highTags:'FIXME', normalTags:'TODO', includePattern: '**/*.java', excludePattern: 'target/**/*')],
            qualityGates: [[threshold: 1, type: 'TOTAL', unstable: true]]
    }

    stage ('Line and Branch Coverage') {
        withMaven {
            sh "mvn -V -U -e jacoco:prepare-agent test jacoco:report -Dmaven.test.failure.ignore"
        }
        publishCoverage adapters: [jacocoAdapter('**/*/jacoco.xml')], 
            sourceFileResolver: sourceFiles('STORE_ALL_BUILD'), 
            calculateDiffForChangeRequests: true
    }

    stage ('Collect Maven Warnings') {
        recordIssues tool: mavenConsole()
    }
}
