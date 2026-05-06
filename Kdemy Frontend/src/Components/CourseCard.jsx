const CourseCard = ({ course, enrolled }) => {
  return (
    <div className="bg-white rounded-lg shadow hover:shadow-lg transition p-4">
      <img
        src="https://source.unsplash.com/400x200/?programming"
        alt="course"
        className="rounded mb-3"
      />

      <h3 className="font-semibold text-lg">{course.title}</h3>
      <p className="text-sm text-gray-500">{course.instructor}</p>

      <div className="mt-2 flex justify-between items-center">
        <span className="font-bold text-purple-600">₹{course.price}</span>

        {enrolled ? (
          <button className="text-sm bg-green-500 text-white px-3 py-1 rounded">
            Continue
          </button>
        ) : (
          <button className="text-sm bg-purple-600 text-white px-3 py-1 rounded">
            Enroll
          </button>
        )}
      </div>
    </div>
  );
};

export default CourseCard;
