<h3 align="center">A Micro Services architecture use case with Spring Cloud  </h3>

<div align="center" >
	
![app](https://user-images.githubusercontent.com/80859231/202856498-9b6b7219-3bbe-423c-b0e8-85a70ed04519.gif)

</div>

---

## 📝 Table of Contents
- [About](#about)
- [Architecture](#Architecture)
- [Config Repository](#config-repo)
- [Consul Registry Service](#consul)
- [Gateway Service](#gateway-service)
- [Customer Micro Service](#customer-service)
- [Inventory Micro Service](#inventory-service)
- [Order Micro Service](#order-service)
- [Security](#security)
- [⛏️ Built Using](#build_using)



## 🧐 About <a name = "about"></a>
a use case for building and connecting micro services with spring cloud , consul for service registration , openfeign for communication between micro services

## 🏁 Architecture <a name = "Architecture"></a>
This is the architecture of our Application
![image](https://user-images.githubusercontent.com/80859231/200179106-7e63ee97-2454-46ed-b007-b4ab4516353b.png)


## 🔧 Config Repository <a name = "config-repo"></a>
the first step is to create a repository that will hold the configuration for all services 
Example : 
![image](https://user-images.githubusercontent.com/80859231/200179252-ec169252-690a-48e2-8eff-bfd9348b16ea.png)


## Consul Registry Service <a name = "consul"></a>
we should now start consul which is a service registration server 
![image](https://user-images.githubusercontent.com/80859231/200179364-c7c1f876-46cb-461e-b833-a5c2ebdd4d8b.png)

```
consul agent -server -bootstrap-expect=1 -data-dir=./tmp-consul -ui -bind=your-ip-here 
```
consul provied a browser interface to visualize all services generally is works on port : 8500

![image](https://user-images.githubusercontent.com/80859231/200179432-5c01549e-7ea9-42b0-9b2b-915efce24f89.png)


## Config Service  <a name = "config-service"></a>
it's just a spring boot application with this dependencies

```
    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-consul-discovery</artifactId>
		</dependency>
```
### configuration in application.properties
```
server.port=8888
spring.application.name=config-service
spring.cloud.config.server.git.uri=/home/zakaria/Desktop/S5/Micro-services/Consul-OpenFeign-Gateway-MicroServices/ecommerce-app/config-repo
spring.cloud.config.server.git.default-label=master
global.params.p1=18
global.params.p2=1881
spring.cloud.consul.discovery.prefer-ip-address=true

```
### Annotations that should be added to main class
![image](https://user-images.githubusercontent.com/80859231/200179840-c8f9972b-9be7-4aa6-bbec-2b6596b3b500.png)

if we start the config service we can see in consul ui that the config service is registred successfully
![image](https://user-images.githubusercontent.com/80859231/200180140-2ba6af32-72f4-49a0-a3af-efddbddcb5dd.png)
also we can get the vars we defined in applications.properties
![image](https://user-images.githubusercontent.com/80859231/200180199-7c5ea1e3-bfc3-4890-b309-a7b79e96d9b6.png)


## Gateway Service <a name = "gateway-service"></a>
this service will handles all the requests also it does the dynamic routing of microservice applications. 
### dependencies
```
    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-consul-discovery</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>

    
```
### application.properties

```
server.port=9999
spring.application.name=gateway-service
spring.config.import=optional:configserver:http://localhost:8888
spring.cloud.consul.discovery.prefer-ip-address=true
```
### configure dynamic routing
<div>in Main Class we added this method </div>

![image](https://user-images.githubusercontent.com/80859231/200181180-fbd1494a-1027-488e-b401-4bea0cd3e20a.png)

## Customer Micro Service   <a name = "customer-service"></a>
### dependencies
```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-consul-discovery</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
```

### application.properties

```
server.port=8081
spring.application.name=customer-service
spring.config.import=optional:configserver:http://localhost:8888 # the uri of the configuration service 

#fix for spring cloud config server service check
spring.cloud.consul.discovery.prefer-ip-address=true

```
### Entities
<div>Customer</div>

![image](https://user-images.githubusercontent.com/80859231/200180449-6d51787c-5cd3-4479-9fde-d42db3fdae5c.png)

<div>CustomerProjection</div>

![image](https://user-images.githubusercontent.com/80859231/200180472-7594d95a-1ede-4a60-bc4e-5d033e19ffc2.png)


### Repositories
<div>CustomerRepository</div>

![image](https://user-images.githubusercontent.com/80859231/200180515-35d23be9-afd6-4219-9ffb-354e5516379f.png)

### Web
<div> CustomerConfigTestController : just to test if we can get the configurations from config service</div>

![image](https://user-images.githubusercontent.com/80859231/200180877-934fc6a1-78da-40b4-aa9a-48e4380fe2ed.png)

### Main Class
i added commandlinerunner bean to create some customers when the app start

![image](https://user-images.githubusercontent.com/80859231/200180966-e6d79d8b-011a-4011-a341-db9b15290641.png)

### Access the App

![image](https://user-images.githubusercontent.com/80859231/200181552-32f577db-35e3-4569-b9c4-78070fadd6b5.png)
![image](https://user-images.githubusercontent.com/80859231/200181601-862ed195-047c-4bfa-89ae-7069423c5c75.png)

## Inventory Service <a name = "inventory-service"></a>
### dependencies

```
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-consul-discovery</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

### application.properties
```
server.port=8082
spring.application.name=inventory-service
spring.config.import=optional:configserver:http://localhost:8888
spring.cloud.consul.discovery.prefer-ip-address=true
```
### Entities
<div>Product</div>

![image](https://user-images.githubusercontent.com/80859231/200181394-d91895d5-84e9-4a0c-8edf-06706c588234.png)

<div>ProductProjection</div>

![image](https://user-images.githubusercontent.com/80859231/200181400-c959d9ed-d395-4c08-9774-07fc3665f633.png)

### Repositories
<div>InventoryRepository</div>

![image](https://user-images.githubusercontent.com/80859231/200181703-5596a3d3-145e-4dd2-a090-a42b90e5cd54.png)

### Main Class
we defined a  commandlinerunner bean to create some products

![image](https://user-images.githubusercontent.com/80859231/200181818-9ee067bb-ff12-4635-b085-0b6e50762319.png)

### Access the Application:

![image](https://user-images.githubusercontent.com/80859231/200181998-7a809218-8b03-415b-b327-28e97e3df51f.png)

![image](https://user-images.githubusercontent.com/80859231/200182010-b198330e-cb26-4dff-9cbd-78383e4a8d86.png)


## Order Service <a name = "order-service"></a>
### dependencies
```
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-consul-discovery</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

### application.properties
```
server.port=8083
spring.application.name=order-service
spring.config.import=optional:configserver:http://localhost:8888
spring.cloud.consul.discovery.prefer-ip-address=true
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:order-db
```
### Entities
<div>Order</div>

![image](https://user-images.githubusercontent.com/80859231/200182063-5b39ea80-04fd-4b21-aef0-acda06839b4b.png)

<div>OrderStatus Enum</div>

![image](https://user-images.githubusercontent.com/80859231/200182098-3d507c7c-8795-4067-b2ec-6d251b054bc1.png)

<div>ProductItem</div>

![image](https://user-images.githubusercontent.com/80859231/200182111-32a817d7-2aa4-4fb0-b94a-b047abc84ba2.png)

### Models

<div>Customer</div>

![image](https://user-images.githubusercontent.com/80859231/200182169-368240ff-92ec-41d2-a9f0-24f448249e2e.png)

<div>Product</div>

![image](https://user-images.githubusercontent.com/80859231/200182192-99ea6a41-6393-416e-9d8b-4d709b6fccb6.png)

### Repositories
<div>OrderRepository</div>

![image](https://user-images.githubusercontent.com/80859231/200182235-bf8df117-15c8-4af3-9b79-7c34e81f5c41.png)


<div>ProductItemRepository</div>

![image](https://user-images.githubusercontent.com/80859231/200182222-2da716d7-e5ed-4ff1-9a56-78e12b0c598b.png)

### Services
<div>CustomerRestService</div>

![image](https://user-images.githubusercontent.com/80859231/200182270-0fd5366b-8937-478c-b980-9a1af7175aaa.png)

<div>InventoryRestService</div>

![image](https://user-images.githubusercontent.com/80859231/200182284-2b939369-b69c-435b-84e3-dff6d31347d7.png)

### Web
<div>OrderRestController</div>

![image](https://user-images.githubusercontent.com/80859231/200182339-c7493198-3040-4439-83fd-5cc613910259.png)

### Main Class

![image](https://user-images.githubusercontent.com/80859231/200182389-ef1ab49b-1a44-4e3e-87d6-50e5ece1270b.png)

### Access the Order App

![image](https://user-images.githubusercontent.com/80859231/200182510-08a29cd5-ad91-48ac-a6f5-8d7d25f214a2.png)

## Security <a name = "security"></a>
For Security we are used keycloak which is an open-source Identity and Access Management (IAM) tool.

### Part1
Starting keycloak

```
kc.sh start-dev 
```

![image](https://user-images.githubusercontent.com/80859231/209342506-df1e8c98-096b-4480-b730-9d3a93cf1c02.png)

Creating Realme

![image](https://user-images.githubusercontent.com/80859231/209342633-84d21326-ed03-4c1a-aa8b-befc0894ba42.png)

Creating Client

![image](https://user-images.githubusercontent.com/80859231/209342726-77175f1e-a60b-4669-8c51-9132924ba6c6.png)

Users

![image](https://user-images.githubusercontent.com/80859231/209342798-0ab764b6-af04-4bc6-a517-8a2a9c267ad5.png)

Roles

![image](https://user-images.githubusercontent.com/80859231/209342846-627de44c-02fb-45b3-99b3-ec76806aaa34.png)

Users in Role Admin

![image](https://user-images.githubusercontent.com/80859231/209342963-9c39b6f7-f4f7-4b47-b0a1-dd5d25695cef.png)

Tests with PostMan

![image](https://user-images.githubusercontent.com/80859231/209343133-21774338-10cc-40fc-97a1-f5876ca91a6d.png)

Decode Token

![image](https://user-images.githubusercontent.com/80859231/209343318-f655e4eb-1683-4d0f-939c-6d43d01e8a1e.png)

Authentication with clientID & client Secret


![image](https://user-images.githubusercontent.com/80859231/209343616-14d3226c-42f0-45f0-9ad2-3ce948dde10f.png)


![image](https://user-images.githubusercontent.com/80859231/209343530-4d8bd874-adaa-430e-bca6-2e87255559cf.png)


## ⛏️ Built Using <a name = "built_using"></a>
- [Spring boot](https://spring.io/projects/spring-boot) - App development
- [Consul](https://www.consul.io/) - Service Registration


## ✍️ Authors <a name = "authors"></a>
- [@zakariamanssouri](https://github.com/zakariamanssouri) 
