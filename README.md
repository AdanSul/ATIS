# ATIS - System Information Together All

## 🧠 Project Overview
**ATIS (System Information Together All)** is a distributed Java-based emergency response system developed for the Ministry of Social Services. It enables users to request help or volunteer during emergency situations. The system is designed using a client-server architecture and operates over a local area network (LAN) in its first version.

## 🎯 Main Features
- 📢 **Distress Button**: Registered users can send distress signals in emergency situations (e.g., medical, safety threats).
- 🤝 **Help Requests**: Users can request assistance such as transportation, babysitting, or buying medicine.
- 👥 **Volunteering**: Other users may volunteer to complete tasks based on help requests.
- 📊 **Community Manager Tools**: Community managers can manage members, approve or reject help requests, view distress call statistics, and generate reports.

## 🗃️ Data Model Overview
- **User**: Name, ID, role (User/Manager), credentials, community affiliation.
- **Distress Call**: User, community, timestamp, optional location/code.
- **Task (Help Request)**: ID, type of help, requester, creation time, volunteer (if any), status (requested, pre-execution, executed), completion time.

## 🧩 System Architecture
- **Backend (Server)**: Java-based application server that handles all system logic, data management, and communication coordination. It manages:
  - Receiving and processing distress calls
  - Managing user sessions and authentication
  - Handling task creation, assignment, and status updates
  - Responding to data requests from clients

- **Frontend (Client)**: Java-based desktop application that runs on user machines and allows interaction with the system. It supports:
  - User login and role-based access
  - Submitting help requests and volunteering for tasks
  - Triggering distress calls
  - Manager views for handling requests and viewing statistics

- **Database**: Relational database (SQL-based) storing all user data, tasks, and distress call records.

- **Communication**: Client-server communication is done via TCP/IP over LAN (local network only), following a request-response protocol.

## 🏗️ Design Goals
- 💡 User-friendly interface and clear role separation.
- 🔐 Secure user identification via login.
- ⚙️ Real-time task updates and synchronization across clients.
- 🔄 Flexible architecture designed for future transition to an internet-accessible version.

## 🧪 Key Functionalities Implemented
- Sending and logging distress calls with validation.
- Creating, approving, rejecting, and assigning help tasks.
- Viewing distress call history with and without histograms.
- Dynamic updates in real-time across all open sessions.
- Visual distinction between user types using color-coded UIs.

## 👨‍👩‍👧‍👦 Team Members
- **Moataz Odeh** – moataz.ody44@gmail.com
- **Siraj Jabarin** – serajwazza@gmail.com
- **Adan Sulaimany** – adanslemany@gmail.com
- **Adan Hammod** – adanhammod@gmail.com
- **Muhammad Shahadeh** – muhammed.sh.181@gmail.com
- **Najm Hijazi** – najm.hijaze@gmail.com

## 👥 Collaboration Summary
Our team began by dividing the project into server-side and client-side components. After facing integration issues, we reorganized by feature: user functions, distress calls, help requests, manager views. We ensured real-time dynamic functionality and consistent UI/UX across components. Every team member contributed to all parts, with shared responsibilities and clear communication.

## 🔧 Challenges & Solutions
We encountered issues in synchronizing client-server communication, especially around real-time events and multi-threaded access. By designing a robust request-handling protocol and using mock user sessions for testing, we overcame these hurdles and ensured stable functionality.

## 📷 UI Highlights
- Color-coded interfaces: Red for distress, Blue for users, Purple for managers.
- Multiple simultaneous user logins supported.
- Real-time data updates across open sessions.

## 📁 Future Work
In the next phase, ATIS will be upgraded to support internet access and a web-based interface, using the flexible architecture already designed.

---

> This project was developed as part of the Java Distributed Systems course. All code and components were created by the project team from scratch with a focus on clean design, reusability, and real-world applicability.

