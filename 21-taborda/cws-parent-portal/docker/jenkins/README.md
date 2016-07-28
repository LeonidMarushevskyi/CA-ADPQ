# CWS Parent Portal Jenkins Container

The CWS Parent Portal Jenkins Container provides continuous delivery automation for the CWS Parent Portal application.

## Configuration

The Jenkins container is configured via the Jenkins web-based user interface, available at
[parentportalbuild.tabordasolutions.net][parentportalbuild]

[parentportalbuild]: http://parentportalbuild.tabordasolutions.net

## Installation

### Prerequisites

1. Docker

### Using Docker

The Jenkins is available as a Docker container from the following registry:

    297353277856.dkr.ecr.us-west-2.amazonaws.com/jenkins

Containers are tagged with the short Git commit hash from the GitHub repository.

## Development Environment

### Prerequisites

No additional prerequisites are required.

### Building

Make sure to commit your changes to the Git repository prior to building to ensure that the tag applied to the
container accurately reflects the container contents.

Use the docker build command to build the container:

    % docker build -t jenkins .

User the docker tag command to tag the image:

    % docker tag jenkins 297353277856.dkr.ecr.us-west-2.amazonaws.com/jenkins:$(git rev-parse --short HEAD)

Authenticate to the EC2 Container Registry (ECR) using the aws ecr command:

    % eval $(aws ecr get-login --region us-west-2)

Push the new image to the container registry using the docker push command:

    % docker push 297353277856.dkr.ecr.us-west-2.amazonaws.com/jenkins:$(git rev-parse --short HEAD)