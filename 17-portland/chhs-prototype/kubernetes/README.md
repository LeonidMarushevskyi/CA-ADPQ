# CHHS Kubernetes Demo 

Kubernetes is an open-source system for automating deployment, scaling, and management of containerized applications. This contains a fully functional provisioning code that can be used to deploy the published Docker images to a high-availability cluster in the Google Compute Engine platform.

## Notes
- High Availability
- Auto Scaling
- Docker based containers 
- Platform Independent 
- Open Source
- This example utilizes a high availability Google Cloud SQL Database [https://cloud.google.com/sql/](https://cloud.google.com/sql/)

### Setup Google Cloud Cluster
This step requires a Google Cloud Compute account and the Google Cloud SDK.

```
gcloud auth login

gcloud config set project GCE_PROJECT_ID
gcloud config set compute/zone us-central1-c
gcloud container clusters create GCE_PROJECT_ID
```

### Kubernetes Secret
The following is used to configure the kubernetes secret that stores sensitive information. This is encrypted in the Kubernetes platform and made available to containers when they are deployed.

This is an example of how to configure the secret for the database connection.

```
kubectl create secret generic chhs \
--from-literal=spring.datasource.username=USERNAME \
--from-literal=spring.datasource.password=PASSWORD \
--from-literal=spring.datasource.url=jdbc:mysql://DB_HOSTNAME:3306/DB_NAME
```

### HTTPS Load Balancer
This is an example of how to configure the secret for the HTTPS load balancer. You need need a Certificate and Private Key file as indicated.

```
echo "
apiVersion: v1
kind: Secret
metadata:
  name: tls
  namespace: default
data:
  tls.crt: `base64 -i ssl/domain.crt`
  tls.key: `base64 -i ssl/domain.key`
type: Opaque
" | kubectl create -f -
```

Firewall rule for load balancer

```
gcloud compute firewall-rules create allow-130-211-0-0-22 \
   --source-ranges 130.211.0.0/22 \
   --allow tcp:30000-32767
```

### Provision the Cluster
Create the cluster

```
kubectl create -f chhs-prototype/
```

Delete the cluster

```
kubectl delete -f chhs-prototype/
```

##### Namespaces
Create a production environment in a different namespace which is completely isolated from other environments.

```
kubectl create -f prod-namespace.yml
kubectl create -f chhs-prototype/ --namespace=prod
```

The above command can also be used to provision stage, qa and other environments to meet the project requirements. You will also need to create the secret in the production namespace using `--namespace`.



### Docker Compose Demo
The Kubernetes platform leverages [Docker] (https://www.docker.com/what-docker) containers for all of it's deployments.

To demonstrate the power of Docker, there is a quick demo of the environment that can be setup locally using Docker Compose. If you have Docker installed, you can launch a local environment using the same publically accessible Docker images used to power the cloud hosted environment. From the current directory, just run the following and visit [http://localhost:8080/](http://localhost:8080/).

```
docker-compose up
```

Please see the `docker-compose.yml` in the current directory to see how this works.