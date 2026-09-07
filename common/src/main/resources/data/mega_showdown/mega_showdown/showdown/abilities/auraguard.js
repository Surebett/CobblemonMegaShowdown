({
  onSourceModifyDamage(damage, source, target, move) {
    if (move.flags["contact"]) return this.chainModify(0.5);
  },
  flags: { breakable: 1 },
  name: "Aura Guard",
  rating: 3.5,
  num: -218
})