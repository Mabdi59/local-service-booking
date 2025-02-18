### Local Service Booking System
This project is a full-stack booking system designed for local contract services such as plumbing, electrical work, and HVAC. Users can seamlessly book services, manage their appointments, and service providers can track inventory for required repairs.

## 🚀 Features
- **User Authentication** – Secure login & registration (JWT-based).
- **Service Booking** – Users can select a service, choose a date & time, and confirm appointments.
- **Admin Dashboard** – Manage bookings, update inventory, and oversee service requests.
- **Inventory Tracking** – Automatically check parts availability before a service appointment.
- **Automated Notifications** – Alerts for service providers if parts need to be picked up.

## 🛠 Tech Stack
### Backend
- Java (Spring Boot)
- PostgreSQL (Database)
- Spring Security (JWT Authentication)
- Email Service (for notifications)

### Frontend
- Vue.js (Vue 3 with Vite)
- Vue Router (Navigation)
- Vuex (State Management)
- Axios (API Requests)

## 📂 Project Structure
```
📦 local-service-booking
 ┣ 📂 backend
 ┃ ┣ 📂 src/main/java/com/localbooking
 ┃ ┃ ┣ 📂 controller   # Handles API endpoints
 ┃ ┃ ┣ 📂 service      # Business logic layer
 ┃ ┃ ┣ 📂 Dao   # Database interactions
 ┃ ┃ ┣ 📂 model        # Entities (Users, Services, Bookings)
 ┃ ┃ ┣ 📂 security     # Authentication & authorization
 ┃ ┃ ┣ 📂 config       # Configuration files
 ┃ ┃ ┗ 📜 Application.java  # Main Spring Boot entry point
 ┣ 📂 frontend
 ┃ ┣ 📂 src
 ┃ ┃ ┣ 📂 views        # Vue pages (Home, Services, Booking, Login)
 ┃ ┃ ┣ 📂 components   # Reusable UI elements
 ┃ ┃ ┣ 📂 store        # Vuex state management
 ┃ ┃ ┣ 📂 router       # Vue Router configuration
 ┃ ┃ ┣ 📜 App.vue      # Main Vue app component
 ┃ ┃ ┗ 📜 main.js      # Vue entry point
 ┣ 📜 README.md        # Project documentation
 ┗ 📜 package.json     # Dependencies and scripts
```

## 🚀 Getting Started
### 1️⃣ Backend Setup
1. Install Java 21 & PostgreSQL.
2. Configure the database connection in `application.properties`.
3. Run the backend:
```sh
mvn spring-boot:run
```

### 2️⃣ Frontend Setup
1. Install Node.js 18+.
2. Navigate to the frontend folder:
```sh
cd frontend
npm install
```
3. Start the development server:
```sh
npm run dev
```

## 🛠 API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | `/api/auth/register` | User registration |
| POST   | `/api/auth/login` | User login |
| GET    | `/api/services` | Retrieve all services |
| POST   | `/api/bookings` | Create a new booking |
| GET    | `/api/bookings/user` | Retrieve user bookings |
| GET    | `/api/admin/bookings` | Admin: View all bookings |
| PATCH  | `/api/admin/inventory` | Update inventory |

## 📌 Next Steps
- ✅ Connect the booking system to Vuex or Backend.
- ✅ Implement email notifications for service providers.
- ✅ Add a real-time admin dashboard.

💡 **Contributions Welcome!** If you have suggestions or feature requests, feel free to open an issue or a pull request! 🚀

