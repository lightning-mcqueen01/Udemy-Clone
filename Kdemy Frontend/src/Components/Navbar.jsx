import { useState } from "react";

const Navbar = ({ onSearch }) => {
  const [query, setQuery] = useState("");

  return (
    <nav className="bg-white shadow-sm px-6 py-3 flex items-center justify-between">
      <h1 className="text-xl font-bold text-purple-600">Kdemy</h1>

      <input
        type="text"
        placeholder="Search for courses..."
        className="w-1/2 px-4 py-2 border rounded-full focus:ring-2 focus:ring-purple-500"
        value={query}
        onChange={(e) => {
          setQuery(e.target.value);
          onSearch(e.target.value);
        }}
      />

      <div className="relative">
        <img
          src="https://i.pravatar.cc/40"
          alt="profile"
          className="rounded-full cursor-pointer"
        />
      </div>
    </nav>
  );
};

export default Navbar;
