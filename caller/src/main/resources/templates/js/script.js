const getDescBox = (e) => {
  const el = e.currentTarget;
  const testName = [...el.classList][1];
  return [...document.querySelectorAll(`.desc-box`)].find((el) =>
    el.querySelector(`.${testName}`)
  );
};

document.addEventListener("DOMContentLoaded", () => {
  const testButtons = document.querySelectorAll(".test-button");
  testButtons.forEach((el) => {
    el.addEventListener("mouseenter", (e) => {
      const descBox = getDescBox(e);
      if (!descBox) return;
      descBox.classList.remove("hide");
    });

    el.addEventListener("mouseleave", (e) => {
      const descBox = getDescBox(e);
      if (!descBox) return;
      descBox.classList.add("hide");
    });

    el.addEventListener("click", () => {
      if (el.querySelector(".spinner")) return;
      const spinner = document.createElement("div");
      spinner.classList.add("spinner");
      el.insertBefore(spinner, el.firstChild);
    });
  });
});
