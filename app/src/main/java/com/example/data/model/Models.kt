package com.example.data.model

enum class UserRole(val label: String, val badge: String) {
  CLIENT("Client", "VIP Client"),
  DESIGNER("Lead Designer", "Studio Architect"),
  PROJECT_MANAGER("Project Manager", "Site Ops"),
  ADMIN("Studio Director", "Executive")
}

enum class SpaceType(val displayName: String, val baseRatePerSqFt: Int) {
  RESIDENTIAL_2BHK("2 BHK Premium Apartment", 1850),
  RESIDENTIAL_3BHK("3 BHK Luxury Residence", 2200),
  RESIDENTIAL_4BHK("4 BHK Grand Residence", 2600),
  PENTHOUSE("Sky Penthouse", 3400),
  LUXURY_VILLA("Royal Villa / Bungalow", 3800),
  COMMERCIAL_OFFICE("Executive Corporate Office", 2100),
  BOUTIQUE_RETAIL("High-End Retail Showroom", 2700),
  CAFE_RESTAURANT("Fine Dining & Lounge", 3100)
}

enum class DesignStyle(
  val title: String,
  val subtitle: String,
  val characteristics: String,
  val paletteName: String
) {
  MODERN_LUXURY(
    "Modern Luxury",
    "Understated opulence with refined metal & stone",
    "Italian Statuario marble, brushed champagne bronze, concealed ambient lighting, minimal joinery.",
    "Obsidian & Champagne Gold"
  ),
  NEO_CLASSICAL(
    "Neo-Classical Royale",
    "Timeless grandeur with contemporary symmetry",
    "Fluted wall moldings, coffered ceilings, crystal chandeliers, chevron oak flooring.",
    "Pearl White & Antique Brass"
  ),
  WARM_JAPANDI(
    "Warm Japandi Zen",
    "Organic tranquility meets functional minimalism",
    "Teak wood louvers, microcement walls, linen textures, indoor bonsai courtyards.",
    "Earthy Taupe & Natural Ash"
  ),
  BIOPHILIC_OPULENCE(
    "Biophilic Opulence",
    "Living architectural greenery framed in luxury",
    "Vertical moss installations, cascading water features, travertine stone, floor-to-ceiling glass.",
    "Deep Emerald & Raw Travertine"
  ),
  INDUSTRIAL_CHIC(
    "Urban Industrial Chic",
    "Raw architectural bones with tailored finesse",
    "Exposed matte black steel, fluted amber glass, reclaimed Burma teak, cognac leather.",
    "Charcoal Grey & Cognac Amber"
  )
}

enum class FinishTier(
  val title: String,
  val multiplier: Float,
  val tagline: String,
  val inclusions: List<String>
) {
  ESSENTIAL_ELEGANCE(
    "Essential Elegance",
    1.0f,
    "Refined baseline with premium Indian materials",
    listOf(
      "Designer False Ceilings with Philips Hue COB lights",
      "High-grade Marine Ply with Merino/Century laminates",
      "Hafele / Hettich soft-close hardware",
      "Asian Paints Royale Aspira luxury wall finish"
    )
  ),
  PREMIUM_LUXURY(
    "Premium Luxury",
    1.45f,
    "Bespoke Italian finishes & imported fittings",
    listOf(
      "Imported Italian Marble flooring & wall cladding",
      "Natural Dyed Veneers with PU/Polyester high gloss",
      "Smart Home Automation (Lutron / KNX lighting)",
      "Blum motorized tandem systems & acoustic panels",
      "Custom velvet & boucle upholstered furnishings"
    )
  ),
  BESPOKE_HAUTE_COUTURE(
    "Haute Couture",
    2.1f,
    "Uncompromising handcrafted master luxury",
    listOf(
      "Bookmatched Onyx & Exotic Brazilian Quartzite",
      "Custom hand-carved joinery & brass inlay accents",
      "Architectural circadian lighting & Bang & Olufsen sound",
      "Full turnkey curated art procurement & bespoke decor",
      "Bespoke imported Italian furniture curation"
    )
  )
}

enum class ProjectPhase(val phaseName: String, val order: Int, val description: String) {
  CONCEPT_MOODBOARD("Concept & Moodboard", 1, "Spatial study, 2D zoning, and aesthetic direction approval"),
  SPATIAL_3D_PLANNING("3D Photoreal Renders", 2, "Detailed 3D walk-throughs, elevation cuts, and lighting plans"),
  MATERIAL_PROCUREMENT("Sourcing & Procurement", 3, "Marble quarry selection, veneer tagging, hardware orders"),
  CIVIL_ELECTRICAL("Civil, HVAC & Electrical", 4, "False ceiling framing, concealed conduit, plumbing & tiling"),
  CARPENTRY_JOINERY("Carpentry & Millwork", 5, "Modular wardrobes, bespoke cabinetry, panelling & polish"),
  STYLING_HANDOVER("Styling & Turnkey Handover", 6, "Furniture installation, art curation, deep cleaning, golden key handover")
}

enum class MaterialCategory(val label: String, val iconName: String) {
  MARBLE_STONE("Marble & Exotic Stone", "stone"),
  VENEER_WOOD("Veneers & Millwork", "forest"),
  FABRIC_LEATHER("Fabrics & Leather", "checkroom"),
  LIGHTING_FIXTURES("Architectural Lighting", "lightbulb"),
  METALS_HARDWARE("Hardware & Finishes", "hardware"),
  WALL_SURFACES("Wall Finishes & Panels", "texture")
}

enum class ConsultationType(val title: String, val locationText: String) {
  IN_STUDIO("In-Studio Meeting", "Antara Studio, 242 Times World, Althan, Surat"),
  ON_SITE("On-Site Spatial Assessment", "Client Property / Site in Surat or Gujarat Region"),
  VIRTUAL_3D("Virtual 3D Video Consultation", "Interactive Google Meet / 3D Screen Share")
}
