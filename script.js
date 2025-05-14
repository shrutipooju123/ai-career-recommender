document.getElementById('careerForm').addEventListener('submit', function (e) {
  e.preventDefault();

  const workType = document.getElementById('workType').value;
  const skillArea = document.getElementById('skillArea').value;
  const result = document.getElementById('result');

  let recommendation = '';

  if (workType === "Data-driven decision making" && skillArea === "Mathematics & Statistics") {
    recommendation = "You should explore a career in Data Science or AI Research.";
  } else if (workType === "Creative problem solving" && skillArea === "Programming") {
    recommendation = "Consider a role as a Full Stack Developer or DevOps Engineer.";
  } else if (workType === "Customer interaction" && skillArea === "Communication") {
    recommendation = "You may enjoy being a Business Analyst or IT Consultant.";
  } else if (workType === "System design & architecture" && skillArea === "Logic & Design") {
    recommendation = "You’re suited for a role as a Solutions Architect or System Engineer.";
  } else {
    recommendation = "Try exploring roles in QA, Support Engineering, or Technical Writing.";
  }

  result.textContent = recommendation;
});
