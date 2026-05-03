import { useEffect, useState } from "react";
import axios from "../api/axios";

const StudentDashboard = () => {
  const [allCourses, setAllCourses] = useState([]);
  const [myCourses, setMyCourses] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchAllCourses();
    fetchMyCourses();
  }, []);

  const fetchAllCourses = async () => {
    try {
      const res = await axios.get("/courses");
      setAllCourses(res.data);
    } catch (err) {
      console.error("Error fetching courses", err);
    }
  };

  const fetchMyCourses = async () => {
    try {
      const res = await axios.get("/enrollment/my-courses");
      setMyCourses(res.data);
    } catch (err) {
      console.error("Error fetching my courses", err);
    } finally {
      setLoading(false);
    }
  };

  const handleEnroll = async (courseId) => {
    try {
      const res = await axios.post(`/payments/create/${courseId}`);
      alert("Payment order created. Complete payment.");
      console.log(res.data);
    } catch (err) {
      alert("Enrollment failed");
    }
  };

  const isEnrolled = (courseId) =>
    myCourses.some((course) => course.id === courseId);

  const filteredCourses = allCourses.filter((course) =>
    course.title.toLowerCase().includes(search.toLowerCase()),
  );

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <p className="text-lg">Loading dashboard...</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100">
      {/* NAVBAR */}
      <Navbar onSearch={setSearch} />

      <div className="p-6 space-y-10">
        {/* MY COURSES */}
        <section>
          <h2 className="text-xl font-bold mb-4">My Courses</h2>

          {myCourses.length === 0 ? (
            <p className="text-gray-500">
              You have not enrolled in any course yet.
            </p>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              {myCourses.map((course) => (
                <CourseCard key={course.id} course={course} enrolled />
              ))}
            </div>
          )}
        </section>

        {/* ALL COURSES */}
        <section>
          <h2 className="text-xl font-bold mb-4">All Courses</h2>

          <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
            {filteredCourses.map((course) => (
              <CourseCard
                key={course.id}
                course={course}
                enrolled={isEnrolled(course.id)}
                onEnroll={handleEnroll}
              />
            ))}
          </div>
        </section>
      </div>
    </div>
  );
};

export default StudentDashboard;
