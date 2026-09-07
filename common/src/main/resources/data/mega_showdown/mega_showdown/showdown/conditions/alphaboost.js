({
    name: "alphaboost",
    onStart(target, source, sourceEffect) {
      const stats = ["atk", "def", "spa", "spd", "spe"];
      const boost = { atk: 0, def: 0, spa: 0, spd: 0, spe: 0 };
      const pool = stats.slice();
      const count = Math.floor(target.level / 10) + 1;
      for (let i = 0; i < count; i++) {
        if (!pool.length)
          break;
        const idx = this.random(pool.length);
        const stat = pool[idx];
        boost[stat] = (boost[stat] ?? 0) + 1;
        if ((boost[stat] ?? 0) >= 6) {
          pool[idx] = pool[pool.length - 1];
          pool.pop();
        }
      }
      let boostName;
      for (boostName in boost) {
        target.alphaBoosts[boostName] = boost[boostName];
      }
      this.add("-start", target, "alphaboost");
    }
})