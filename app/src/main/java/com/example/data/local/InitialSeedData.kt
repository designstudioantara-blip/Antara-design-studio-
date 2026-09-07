package com.example.data.local

object InitialSeedData {
  val initialProjects = listOf(
    ProjectEntity(
      id = 1,
      title = "The Althan Sky Penthouse",
      clientName = "Mr. Rajesh & Ananya Mehta",
      clientPhone = "+91 98251 44920",
      location = "Althan Canal Road, Surat",
      spaceType = "Sky Penthouse",
      totalAreaSqFt = 4850,
      budgetLakhs = 85.0,
      progressPercent = 68,
      currentPhase = "Carpentry & Millwork",
      startDate = "12 Oct 2024",
      targetHandoverDate = "15 Apr 2025",
      leadDesigner = "Ar. Hardik Patel",
      projectManager = "Er. Sunny Shah",
      status = "In Progress",
      coverImageUrl = "penthouse_hero",
      unreadUpdatesCount = 2
    ),
    ProjectEntity(
      id = 2,
      title = "Times World Corporate Suite",
      clientName = "Vanguard Tech Capital",
      clientPhone = "+91 97253 14309",
      location = "Times World, Althan, Surat",
      spaceType = "Executive Corporate Office",
      totalAreaSqFt = 3200,
      budgetLakhs = 52.0,
      progressPercent = 92,
      currentPhase = "Styling & Turnkey Handover",
      startDate = "01 Aug 2024",
      targetHandoverDate = "28 Feb 2025",
      leadDesigner = "Ar. Sneha Desai",
      projectManager = "Er. Sunny Shah",
      status = "Finishing",
      coverImageUrl = "office_hero",
      unreadUpdatesCount = 0
    ),
    ProjectEntity(
      id = 3,
      title = "Vesu Royal Estate Bungalow",
      clientName = "Dr. Vikram Singhania",
      clientPhone = "+91 99090 12388",
      location = "VIP Road, Vesu, Surat",
      spaceType = "Royal Villa / Bungalow",
      totalAreaSqFt = 7200,
      budgetLakhs = 140.0,
      progressPercent = 35,
      currentPhase = "Civil, HVAC & Electrical",
      startDate = "15 Jan 2025",
      targetHandoverDate = "30 Sep 2025",
      leadDesigner = "Ar. Hardik Patel",
      projectManager = "Er. Ketan Solanki",
      status = "In Progress",
      coverImageUrl = "villa_hero",
      unreadUpdatesCount = 1
    )
  )

  val initialPortfolio = listOf(
    PortfolioItemEntity(
      id = 1,
      title = "The Obsidian Grand Salon",
      category = "Penthouse",
      location = "Althan, Surat",
      areaSqFt = 5200,
      style = "Modern Luxury",
      tagline = "Double-height living space with bookmatched Italian Statuario and champagne bronze accents.",
      description = "Designed for a discerning collector family, this sky-mansion merges floor-to-ceiling panoramic fenestrations with monolithic stone statement walls, custom floating veneer TV consoles, and a bespoke circadian lighting architecture.",
      beforeImageDesc = "Bare shell concrete structure with open conduit lines and rough brick partitions.",
      afterImageDesc = "Immaculately finished open salon featuring custom curved boucle sofas, bookmatched Statuario marble wall with recessed amber lighting, fluted smoked oak panelling, and brushed gold floor-to-ceiling partition screen.",
      keyFeaturesJson = "Bookmatched Statuario, Custom Italian Joinery, Circadian Automation, Double-height Chandelier, Concealed HVAC",
      isFeatured = true,
      completionYear = "2024"
    ),
    PortfolioItemEntity(
      id = 2,
      title = "Vesu Heritage Contemporary Villa",
      category = "Villa",
      location = "Vesu, Surat",
      areaSqFt = 8500,
      style = "Neo-Classical Royale",
      tagline = "Harmonious synthesis of classical European symmetry and Indian artisanal craftsmanship.",
      description = "A stately residential estate featuring an internal courtyard with marble water cascades, fluted limestone columns, hand-carved Burma teak millwork, and private spa bathrooms lined in exotic Calacatta Gold.",
      beforeImageDesc = "Old 1990s civil structure with dark congested corridors and low false ceilings.",
      afterImageDesc = "Transformed into an airy, illuminated neoclassical estate with grand entry foyer, coffered ceilings with gold leafing, chevron French oak parquet flooring, and central courtyard glass skylight.",
      keyFeaturesJson = "Courtyard Water Cascade, Calacatta Gold Marble, Hand-carved Burma Teak, Coffered Gold Leaf Ceilings",
      isFeatured = true,
      completionYear = "2024"
    ),
    PortfolioItemEntity(
      id = 3,
      title = "Monochrome Executive Boardroom & Lounge",
      category = "Commercial",
      location = "Times World, Surat",
      areaSqFt = 4100,
      style = "Industrial Opulence",
      tagline = "Sophisticated corporate headquarters exuding authority, privacy, and precision.",
      description = "Created for a global diamond trading firm, incorporating switchable smart-glass partitions, acoustic suede wall paneling, monolithic Nero Marquina boardroom table with integrated wireless charging, and an executive whiskey lounge.",
      beforeImageDesc = "Standard commercial warm-shell commercial unit with generic tile floors.",
      afterImageDesc = "Sleek architectural boardroom with seamless black quartz monolith table, soundproofed fluted charcoal wall cladding, smart privacy switchable glass, and curated architectural downlights.",
      keyFeaturesJson = "Smart Privacy Glass, Acoustic Suede Panels, Nero Marquina Monolith, Concealed Bar Lounge",
      isFeatured = false,
      completionYear = "2023"
    ),
    PortfolioItemEntity(
      id = 4,
      title = "Dumas Coastline Biophilic Residence",
      category = "Residential",
      location = "Dumas Road, Surat",
      areaSqFt = 3600,
      style = "Warm Japandi Zen",
      tagline = "Serene coastal sanctuary celebrating organic raw textures and muted earth tones.",
      description = "Emphasizes natural ventilation, microcement floor finishes, custom rattan storage units, indoor tropical planter beds, and soft linen drapes framing sea breeze vistas.",
      beforeImageDesc = "Traditional 4BHK apartment with boxed compartmentalized rooms.",
      afterImageDesc = "Demolished non-structural walls to establish open-plan living, continuous microcement seamless floor, teak wood slatted screens, and integrated sunken lounge seating.",
      keyFeaturesJson = "Seamless Microcement, Organic Teak Louvers, Indoor Zen Garden, Sunken Conversation Pit",
      isFeatured = true,
      completionYear = "2024"
    ),
    PortfolioItemEntity(
      id = 5,
      title = "Aura Fine Jewelry Flagship Atelier",
      category = "Commercial",
      location = "Ghod Dod Road, Surat",
      areaSqFt = 2800,
      style = "Modern Luxury",
      tagline = "Boutique luxury retail experience crafted with curved velvet alcoves and museum-grade display lighting.",
      description = "High-security luxury retail space designed with curved blush velvet private viewing salons, motorized biometric vitrines, and custom bronze filigree screens inspired by diamond facets.",
      beforeImageDesc = "Empty commercial showroom with raw concrete pillars.",
      afterImageDesc = "Opulent flagship salon with custom velvet banquettes, brushed brass trim display showcases, high CRI 98 museum lighting, and private VIP consulting chambers.",
      keyFeaturesJson = "Museum-Grade 98 CRI Lighting, Biometric Vitrines, Custom Velvet Alcoves, Brass Filigree Screen",
      isFeatured = false,
      completionYear = "2024"
    )
  )

  val initialMaterials = listOf(
    MaterialSampleEntity(
      id = 1,
      name = "Statuario Extra First Choice Marble",
      category = "Marble & Exotic Stone",
      brandOrigin = "Carrara, Italy",
      specification = "20mm thickness, bookmatched slab, mirror polished finish with subtle grey & gold veining.",
      costTier = "$$$$$",
      status = "Approved by Client",
      roomZone = "Living Grand Salon (Flooring & TV Accent Wall)",
      isClientApproved = true,
      sampleRequested = false
    ),
    MaterialSampleEntity(
      id = 2,
      name = "Smoked European Oak Veneer",
      category = "Veneers & Millwork",
      brandOrigin = "DecoWood / Oberflex France",
      specification = "Natural dyed veneer with ultra-matte open-pore polyurethane coat, zero VOC.",
      costTier = "$$$$",
      status = "Approved by Client",
      roomZone = "Master Suite & Corridor Panelling",
      isClientApproved = true,
      sampleRequested = false
    ),
    MaterialSampleEntity(
      id = 3,
      name = "Dedar Milano Ribbed Boucle & Velvet",
      category = "Fabrics & Leather",
      brandOrigin = "Milan, Italy",
      specification = "High-abrasion 80,000 Martindale rub count, stain-resistant luxury upholstery fabric.",
      costTier = "$$$$",
      status = "Under Review",
      roomZone = "Curved Salon Sofas & Dining Chairs",
      isClientApproved = false,
      sampleRequested = true
    ),
    MaterialSampleEntity(
      id = 4,
      name = "Brushed Champagne Bronze Profiles",
      category = "Hardware & Finishes",
      brandOrigin = "Hafele Master Collection, Germany",
      specification = "PVD coated anti-fingerprint aluminum architectural framing profiles and handles.",
      costTier = "$$$",
      status = "Approved by Client",
      roomZone = "Wardrobe Doors & Partition Screens",
      isClientApproved = true,
      sampleRequested = false
    ),
    MaterialSampleEntity(
      id = 5,
      name = "Flos Trimless Architectural Magnetic Track Lights",
      category = "Architectural Lighting",
      brandOrigin = "Flos, Italy",
      specification = "DALI dimmable 2700K Warm White, 95+ CRI high-efficiency magnetic accent modules.",
      costTier = "$$$$$",
      status = "Under Review",
      roomZone = "All Living & Master Ceiling Recesses",
      isClientApproved = false,
      sampleRequested = false
    ),
    MaterialSampleEntity(
      id = 6,
      name = "Italian Lime Stucco Micro-Venetian Plaster",
      category = "Wall Finishes & Panels",
      brandOrigin = "Novacolor, Italy",
      specification = "Artisanal hand-troweled mineral plaster with subtle wax sheen and marble dust texture.",
      costTier = "$$$$",
      status = "Approved by Client",
      roomZone = "Entrance Foyer & Powder Room Accent",
      isClientApproved = true,
      sampleRequested = false
    )
  )

  val initialChatMessages = listOf(
    ChatMessageEntity(
      id = 1,
      projectId = 1,
      senderName = "Ar. Hardik Patel",
      senderRole = "Lead Designer",
      messageText = "Namaste Rajesh ji! We have completed the millwork framing for the Master Suite wardrobe and the fluted wood panelling. The mock-up looks exquisite.",
      timestamp = "Yesterday, 4:15 PM",
      isActionableCard = false,
      isFromClient = false
    ),
    ChatMessageEntity(
      id = 2,
      projectId = 1,
      senderName = "Mr. Rajesh Mehta",
      senderRole = "Client",
      messageText = "Hello Hardik! The latest site photos look very promising. For the salon curved sofa upholstery, Ananya and I reviewed the Dedar Milano boucle swatch.",
      timestamp = "Yesterday, 5:30 PM",
      isActionableCard = false,
      isFromClient = true
    ),
    ChatMessageEntity(
      id = 3,
      projectId = 1,
      senderName = "Ar. Hardik Patel",
      senderRole = "Lead Designer",
      messageText = "Great! Here is the swatch approval card for the Dedar Milano Warm Ivory Boucle. Please review and tap approve so we can dispatch the fabric cutting order.",
      timestamp = "Today, 10:05 AM",
      isActionableCard = true,
      actionTitle = "Approve Material: Dedar Milano Warm Ivory Boucle",
      isCompletedAction = false,
      isFromClient = false
    ),
    ChatMessageEntity(
      id = 4,
      projectId = 1,
      senderName = "Er. Sunny Shah",
      senderRole = "Project Manager",
      messageText = "Site update: False ceiling laser leveling completed in living salon. Flos magnetic tracks arrived on site today. Ready for electrical wiring inspection tomorrow.",
      timestamp = "Today, 11:40 AM",
      isActionableCard = false,
      isFromClient = false
    )
  )

  val initialInvoices = listOf(
    InvoiceEntity(
      id = 1,
      invoiceNumber = "INV-ADS-2024-089",
      milestoneTitle = "Phase 1: Concept & 3D Visuals Signoff (30%)",
      milestonePercentage = 30,
      amountInRupees = 2550000,
      dueDate = "15 Nov 2024",
      status = "Paid",
      paidDate = "14 Nov 2024",
      transactionRef = "HDFC0092183921"
    ),
    InvoiceEntity(
      id = 2,
      invoiceNumber = "INV-ADS-2024-114",
      milestoneTitle = "Phase 2: Civil, Stone Quarry & Procurement (40%)",
      milestonePercentage = 40,
      amountInRupees = 3400000,
      dueDate = "10 Jan 2025",
      status = "Paid",
      paidDate = "08 Jan 2025",
      transactionRef = "ICIC008219401"
    ),
    InvoiceEntity(
      id = 3,
      invoiceNumber = "INV-ADS-2025-018",
      milestoneTitle = "Phase 3: Carpentry, Millwork & Automation (20%)",
      milestonePercentage = 20,
      amountInRupees = 1700000,
      dueDate = "05 Mar 2025",
      status = "Pending Approval",
      paidDate = "",
      transactionRef = ""
    ),
    InvoiceEntity(
      id = 4,
      invoiceNumber = "INV-ADS-2025-045",
      milestoneTitle = "Phase 4: Styling, Curation & Golden Key Handover (10%)",
      milestonePercentage = 10,
      amountInRupees = 850000,
      dueDate = "15 Apr 2025",
      status = "Upcoming",
      paidDate = "",
      transactionRef = ""
    )
  )

  val initialConsultations = listOf(
    ConsultationEntity(
      id = 1,
      clientName = "Nirav & Pooja Shah",
      clientPhone = "+91 98980 55123",
      clientEmail = "nirav.shah@gmail.com",
      preferredDate = "02 Mar 2025",
      preferredTimeSlot = "4:00 PM - 5:30 PM",
      consultationType = "In-Studio Meeting",
      spaceType = "4 BHK Grand Residence",
      approximateSqFt = 3400,
      budgetRange = "₹50 - ₹75 Lakhs",
      siteAddress = "Avadh Copper Stone, Vesu, Surat",
      specialRequirements = "Italian marble flooring, open kitchen island, luxury master walk-in wardrobe with smart lighting.",
      status = "Confirmed"
    ),
    ConsultationEntity(
      id = 2,
      clientName = "Kunal Kothari",
      clientPhone = "+91 97129 44332",
      clientEmail = "kothari.kunal@vanguard.in",
      preferredDate = "05 Mar 2025",
      preferredTimeSlot = "11:00 AM - 12:30 PM",
      consultationType = "On-Site Spatial Assessment",
      spaceType = "High-End Retail Showroom",
      approximateSqFt = 2200,
      budgetRange = "₹40 - ₹60 Lakhs",
      siteAddress = "Times Galleria, Althan, Surat",
      specialRequirements = "High-end jewelry and diamond trading boutique with biometric security chambers and private lounge.",
      status = "Confirmed"
    )
  )
}
